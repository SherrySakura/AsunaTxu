package com.asuna.textutils.service;

import com.asuna.textutils.entity.Task;
import com.asuna.textutils.entity.TemplateResultBase;
import com.asuna.textutils.entity.TranslateAPIProperty;
import com.asuna.textutils.entity.TranslateResult;
import com.asuna.textutils.http.TransRequest;
import com.asuna.textutils.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TextHandler {

    private final int maxTimesWithoutLogin = 50;

    private int timesWithoutLogin = 0;
    @Autowired
    private TranslateAPIProperty translateAPIProperty;

    @Autowired
    private MissionHandler missionHandler;

    @Autowired
    private TaskHandler taskHandler;

    @Autowired
    private AccountHandler accountHandler;

    public TextHandler() {
    }

    /**
     * 去除多余空格服务
     * @param ori
     * @return
     */
    public String dropBlank(String ori, Integer uuid){
        String handled = TextUtils.dropBlank(ori);
        if (uuid != null){
            missionHandler.setUuid(uuid);
            missionHandler.getCurrentMission();
            int len = ori.length();
            missionHandler.increaseCommit(len);
            missionHandler.save();

            Task curTask = taskHandler.createBlankHandlerTask(len, ori, handled);
            taskHandler.insertNewTask(uuid, curTask);

            accountHandler.setUuid(uuid);
            accountHandler.updateExp(len);
        }
        return handled;
    }

    /**
     * 给予未登录用户一定免费使用次数
     * @return
     */
    private boolean checkUnloginAuth(){
        timesWithoutLogin++;
        if (timesWithoutLogin > maxTimesWithoutLogin){
            return false;
        }
        return true;
    }

    /**
     * 将文本中的中文标点转换为英文标点
     * @param ori
     * @return
     */
    public String toggleDot(String ori, Integer uuid){
        String result = "";
        result = dropBlank(ori, uuid);
        char[] s = result.toCharArray();
        TextUtils.toggleDotCNToEN(s);
        result = new String(s);
        return result;
    }

    /**
     * 翻译服务，接入有道翻译API
     * @param ori
     * @return
     */
    public TemplateResultBase<TranslateResult> translateService(String ori, Integer uuid) throws IOException, NoSuchAlgorithmException, JSONException {
        String str = TextUtils.dropBlank(ori);
        int len = str.length();
        Map<String, String> params = new HashMap<>();

        String salt = String.valueOf(System.currentTimeMillis());
        params.put("q", str);
        params.put("from", translateAPIProperty.getFrom());
        params.put("to", translateAPIProperty.getTo());
        params.put("signType", translateAPIProperty.getSignType());
        params.put("appKey", translateAPIProperty.getAppKey());
        params.put("salt", salt);
        String curTime = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("curtime", curTime);
        String signOri = translateAPIProperty.getAppKey() + GenUtils.truncate(str) + salt + curTime + translateAPIProperty.getAppSeret();
        String sign = GenUtils.getSHA256(signOri);
        params.put("sign", sign);
        TransRequest request = new TransRequest(translateAPIProperty.getUrl(), params);
        TemplateResultBase<TranslateResult> result = request.excute();
        if (uuid != null){
            missionHandler.setUuid(uuid);
            missionHandler.getCurrentMission();
            missionHandler.increaseTrans(len);
            missionHandler.save();

            Task curTask = taskHandler.createTranslateHandlerTask(len, ori, result.getData().getTranslate());
            taskHandler.insertNewTask(uuid, curTask);

            accountHandler.setUuid(uuid);
            accountHandler.updateExp(len);
        }
        return result;
    }
}

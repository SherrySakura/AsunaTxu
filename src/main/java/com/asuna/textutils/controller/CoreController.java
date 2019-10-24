package com.asuna.textutils.controller;


import com.asuna.textutils.entity.*;
import com.asuna.textutils.service.TextHandler;
import com.asuna.textutils.service.AccountHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class CoreController {

    @Autowired
    private TranslateAPIProperty translateAPIProperty;

    @Autowired
    private AccountHandler handler;

    @Autowired
    private TextHandler textHandler;

    @RequestMapping(value = "/blank", method = RequestMethod.POST)
    public Object getTextWithoutBlank(String message, Integer uuid){
        TemplateResultBase<TextHandleResult> result = new TemplateResultBase<>();
        String res = textHandler.dropBlank(message, uuid);
        TextHandleResult handleResult = new TextHandleResult(res);
        result.setStatus(200, handleResult, "处理成功");
        return result;
    }

    @RequestMapping(value = "/toggle", method = RequestMethod.POST)
    public Object getToggleText(String message, Integer uuid){
        TemplateResultBase<TextHandleResult> result = new TemplateResultBase<>();
        String res = textHandler.toggleDot(message, uuid);
        TextHandleResult handleResult = new TextHandleResult(res);
        result.setStatus(200, handleResult, "处理成功");
        return result;
    }

    @RequestMapping(value = "/trans", method = RequestMethod.POST)
    public Object trans(String message, Integer uuid){
        TemplateResultBase<TranslateResult> result = null;
        try {
            result = textHandler.translateService(message, uuid);
        } catch (IOException | NoSuchAlgorithmException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 登录处理
     * @param name
     * @param password
     * @param remember
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(String name, String password, boolean remember){
        return handler.loginHandler(name, password);
    }

    /**
     * 注册处理
     * @param account
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object regist(Account account){
        return handler.registHandler(account);
    }


}

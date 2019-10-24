package com.asuna.textutils.utils;

import com.asuna.textutils.entity.TemplateResultBase;
import com.asuna.textutils.entity.TranslateResult;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public class GenUtils {

    /**
     * 判断一个字符是否是一个英文字母
     * @param c
     * @return
     */
    public static boolean isEnglishOrDigit(char c){
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '.') || (c == ',')){
            return true;
        }
        return false;
    }

    /**
     * 将一个长的字符串切割为一个短的字符串，按照字符长度确定
     * 若长度小于20，则直接返回原始字符串
     * 若大于20，则取前10个字符，拼接上长度，再拼接上后20个字符
     * @param ori
     * @return
     */
    public static String truncate(String ori){
        if (ori == null){
            return null;
        }
        int length = ori.length();
        return length <= 20 ? ori : (ori.substring(0, 10) + length + ori.substring(length - 10, length));
    }

    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    public static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * 获取一个字符串的SHA256加密后的结果字符串
     * @param ori
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getSHA256(String ori) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String encode = "";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(ori.getBytes("UTF-8"));
        encode = byte2Hex(md.digest());
        return encode;
    }

    /**
     * 将翻译API的返回json结果转换为本地pojo过程
     * @param json
     * @return
     * @throws JSONException
     */
    public static TemplateResultBase<TranslateResult> jsonToTranslateResult(String json) throws JSONException {
        TranslateResult result = new TranslateResult();
        JsonParser parser = new JacksonJsonParser();
        Map<String, Object> stringObjectMap = parser.parseMap(json);
        result.setErrorCode((String) stringObjectMap.get("errorCode"));
        if (!result.getErrorCode().equals("0")){
            result.setQuery((String) stringObjectMap.get("query"));
            result.setL((String) stringObjectMap.get("l"));
            return checkResult(result);
        }
        result.setQuery((String) stringObjectMap.get("query"));
        result.setL((String) stringObjectMap.get("l"));
        List<String> array = (List<String>) stringObjectMap.get("translation");
        result.setTranslate(array.get(0));
        return checkResult(result);
    }

    public static TemplateResultBase<TranslateResult> checkResult(TranslateResult result){
        TemplateResultBase<TranslateResult> resultBase = new TemplateResultBase<>();
        String code = result.getErrorCode();
        switch (code){
            case "0":
                resultBase.set200(result);
                break;
            case "113":
                resultBase.set400(result);
                break;
            case "411":
                resultBase.set401(result);
                break;
            case "103":
                resultBase.set403(result);
                break;
                default:
                    resultBase.set500(result);
                    break;
        }
        return resultBase;
    }

    public static boolean validateID(String id){
        boolean res = true;
        if (id == null || "".equals(id)){
            res = false;
        }
        return res;
    }
}

package com.asuna.textutils.entity;

/**
 * 有道翻译API返回的结果json封装成本地pojo对象
 */
public class TranslateResult {

    private String errorCode;
    private String query;
    private String translate;
    private String l;

    public TranslateResult() {
    }

    public TranslateResult(String errorCode, String query, String translate, String l) {
        this.errorCode = errorCode;
        this.query = query;
        this.translate = translate;
        this.l = l;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }
}

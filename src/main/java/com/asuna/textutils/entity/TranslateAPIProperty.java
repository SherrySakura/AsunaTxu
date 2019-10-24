package com.asuna.textutils.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "trans")
public class TranslateAPIProperty {

    private String url;
    private String appKey;
    private String appSeret;
    private String signType;
    private String from;
    private String to;

    public TranslateAPIProperty() {
    }

    public TranslateAPIProperty(String url, String appKey, String appSeret, String signType, String from, String to) {
        this.url = url;
        this.appKey = appKey;
        this.appSeret = appSeret;
        this.signType = signType;
        this.from = from;
        this.to = to;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSeret() {
        return appSeret;
    }

    public void setAppSeret(String appSeret) {
        this.appSeret = appSeret;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

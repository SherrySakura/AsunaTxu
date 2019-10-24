package com.asuna.textutils.entity;

public class TextHandleResult {
    private String result;
    private int len;

    public TextHandleResult() {
    }

    public TextHandleResult(String result) {
        this.result = result;
        this.len = result.length();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}

package com.asuna.textutils.entity;

public class TemplateTableResult<T> extends TemplateResultBase<T> {

    private int count;

    public TemplateTableResult() {
    }

    public TemplateTableResult(int count) {
        this.count = count;
    }

    public TemplateTableResult(int code, T data, String msg, int count) {
        super(code, data, msg);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStatus(int code, T data, String msg, int count){
        super.setStatus(code, data, msg);
        this.count = count;
    }
}

package com.asuna.textutils.entity;

public class TemplateResultBase<T> {
    private int code;
    private T data;
    private String msg;

    public TemplateResultBase() {
    }

    public TemplateResultBase(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 全局成功，200状态码模板
     * @param data
     */
    public void set200(T data){
        code = 200;
        msg = "查询成功";
        this.data = data;
    }

    /**
     * 待翻译的文本不能为空
     * @param data
     */
    public void set400(T data){
        code = 400;
        msg = "待翻译的文本不能为空";
        this.data = data;
    }

    /**
     * 查询后端错误
     * @param data
     */
    public void set500(T data){
        code = 500;
        msg = "查询后端错误";
        this.data = data;
    }

    public void set401(T data){
        code = 401;
        msg = "请求次数过于频繁";
        this.data = data;
    }

    /**
     * 请求的文本过长
     * @param data
     */
    public void set403(T data){
        code = 403;
        msg = "请求的文本过长";
        this.data = data;
    }

    /**
     * 自定义设置返回状态码和数据实体以及消息
     * @param code
     * @param data
     * @param msg
     */
    public void setStatus(int code, T data, String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}

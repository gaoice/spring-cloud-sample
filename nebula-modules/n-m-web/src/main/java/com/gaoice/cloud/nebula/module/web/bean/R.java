package com.gaoice.cloud.nebula.module.web.bean;

/**
 * @author gaoice
 */
public class R<T> {

    private long code;

    private String msg;

    private T data;

    public R(long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

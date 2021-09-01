package com.gaoice.cloud.nebula.common;

/**
 * @author gaoice
 */
public class Result<T> {

    private long code;

    private String msg = "";

    private T data;

    public long getCode() {
        return code;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = ResultCodeEnum.SUCCESS.getCode();
        result.data = data;
        return result;
    }

    public static <T> Result<T> failed() {
        return result(ResultCodeEnum.FAILED, null);
    }

    public static <T> Result<T> failed(String msg) {
        return failed(msg, null);
    }

    public static <T> Result<T> failed(String msg, T data) {
        Result<T> result = new Result<>();
        result.code = ResultCodeEnum.FAILED.getCode();
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static <T> Result<T> result(ResultCodeEnum codeEnum, T data) {
        Result<T> result = new Result<>();
        result.code = codeEnum.getCode();
        result.msg = codeEnum.getMsg();
        result.data = data;
        return result;
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

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

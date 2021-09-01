package com.gaoice.cloud.nebula.common;

/**
 * @author gaoice
 */
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    FAILED(500, "失败"),
    UNAUTHORIZED(401, "未登录或token失效"),
    FORBIDDEN(403, "无权限");

    private final long code;

    private final String msg;

    ResultCodeEnum(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

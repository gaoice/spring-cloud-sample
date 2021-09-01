package com.gaoice.cloud.nebula.module.web.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author gaoice
 */
public class RAdapter extends R<String> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 反序列化时忽略不存在的属性
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public RAdapter(long code, String msg, String data) {
        super(code, msg, data);
    }

    public <T> T getForObject(Class<T> dataType) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(getData(), dataType);
    }
}

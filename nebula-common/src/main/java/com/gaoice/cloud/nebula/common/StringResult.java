package com.gaoice.cloud.nebula.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gaoice.cloud.nebula.common.util.JSON;

/**
 * @author gaoice
 */
public class StringResult extends Result<String> {

    public <T> T getDataForObject(Class<T> c) throws JsonProcessingException {
        return JSON.toObject(getData(), c);
    }
}

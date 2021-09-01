package com.gaoice.cloud.nebula.common.util;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * @author gaoice
 */
public class SimpleTests {

    @Test
    public void testJson() throws Exception {
        String s = "{\"username\":\"un\",\"localDate\":\"2016-06-30\"}";
        Object o = JSON.toObject(s, SimpleBean.class);
        System.out.println(o);
    }

    @Data
    public static class SimpleBean {
        private String username;
        private LocalDate localDate;
    }
}

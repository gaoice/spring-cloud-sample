package com.gaoice.cloud.nebula.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gaoice
 */
@Component
@ConfigurationProperties("nebula.gateway")
public class AppProperties {

    private List<String> publicUrls;

    public List<String> getPublicUrls() {
        return publicUrls;
    }

    public void setPublicUrls(List<String> publicUrls) {
        this.publicUrls = publicUrls;
    }
}

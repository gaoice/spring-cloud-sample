package com.gaoice.cloud.nebula.service.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author gaoice
 */
@Data
@EnableConfigurationProperties
@ConfigurationProperties("nebula.user")
@Component
public class AppProperties {

    private String clientId = "n-s-user";

    private String clientSecret = "123456";

    private String oauthTokenUrl = "http://nebula-auth/oauth/token";
}

package com.gaoice.cloud.nebula.auth.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.web.client.RestTemplate;

import java.security.KeyPair;

/**
 * @author gaoice
 */
@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // oauth 相关的 bean
    @Bean
    public KeyPair keyPair(AppProperties appProperties) {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(appProperties.getJksPath()), appProperties.getJksStorePass().toCharArray());
        return keyStoreKeyFactory.getKeyPair(appProperties.getJksAlias(), appProperties.getJksKeyPass().toCharArray());
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(KeyPair keyPair) {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenEnhancer jwtTokenEnhance() {
        // todo: ...
        return (accessToken, authentication) -> accessToken;
    }
}

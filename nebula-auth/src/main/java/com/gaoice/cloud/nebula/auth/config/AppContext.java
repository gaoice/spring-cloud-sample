package com.gaoice.cloud.nebula.auth.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gaoice
 */
@Component
public class AppContext {
    private final ConcurrentHashMap<String, String> clientMap = new ConcurrentHashMap<>();

    public AppContext(AppProperties appProperties) {
        appProperties.getClients().forEach(c -> clientMap.put(c.getClientId(), c.getUserServiceUrl()));
    }

    public String getUrl(String clientId) {
        return clientMap.get(clientId);
    }
}

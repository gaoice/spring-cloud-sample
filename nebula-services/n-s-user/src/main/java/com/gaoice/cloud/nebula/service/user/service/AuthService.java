package com.gaoice.cloud.nebula.service.user.service;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author gaoice
 */
@FeignClient(value = "nebula-auth", fallbackFactory = AuthService.AuthServiceFallbackFactory.class)
public interface AuthService {

    @PostMapping("/oauth/token")
    Map<String, String> getToken(@RequestParam Map<String, String> param);

    class AuthServiceFallbackFactory implements FallbackFactory<AuthService> {

        @Override
        public AuthService create(Throwable throwable) {
            return null;
        }
    }
}

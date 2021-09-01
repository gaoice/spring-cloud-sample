package com.gaoice.cloud.nebula.service.user.controller;

import com.gaoice.cloud.nebula.common.Result;
import com.gaoice.cloud.nebula.module.web.controller.AbstractController;
import com.gaoice.cloud.nebula.service.user.config.AppProperties;
import com.gaoice.cloud.nebula.service.user.dto.LoginParam;
import com.gaoice.cloud.nebula.service.user.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoice
 */
@AllArgsConstructor
@RestController
public class LoginController extends AbstractController {

    private final AuthService authService;

    private final AppProperties ap;

    private final RestTemplate restTemplate;

    @PostMapping("/login")
    public Result<Map<String, String>> login(LoginParam loginParam) {
        Map<String, String> param = new HashMap<>();
        param.put("client_id", ap.getClientId());
        param.put("client_secret", ap.getClientSecret());
        param.put("grant_type", "password");
        param.put("username", loginParam.getUsername());
        param.put("password", loginParam.getPassword());

        Map<String, String> tokenResult;
        try {
            tokenResult = authService.getToken(param);
        } catch (Exception e) {
            logger.error("nebula-auth 请求失败", e);
            String errorMsg = e.getMessage();
            if (errorMsg.contains("invalid_grant")) {
                return Result.failed("用户名或密码错误");
            }
            return Result.failed("内部错误");
        }

        return Result.success(tokenResult);
    }
}

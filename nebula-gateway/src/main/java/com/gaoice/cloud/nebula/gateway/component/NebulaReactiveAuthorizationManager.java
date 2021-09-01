package com.gaoice.cloud.nebula.gateway.component;

import com.gaoice.cloud.nebula.gateway.config.AppProperties;
import com.gaoice.cloud.nebula.gateway.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.List;

/**
 * @author gaoice
 */
@Component
public class NebulaReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NebulaReactiveAuthorizationManager.class);

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Autowired
    private AppProperties appProperties;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();

        // 白名单放行
        List<String> publicUrls = appProperties.getPublicUrls();
        for (String pu : publicUrls) {
            if (ANT_PATH_MATCHER.match(pu, path)) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }

        // 解析 token  验证权限
        try {
            String token = TokenUtils.getToken(request);
            String payload = TokenUtils.getPayload(token);
            //
        } catch (ParseException e) {
            LOGGER.error("token 解析错误", e);
            return Mono.just(new AuthorizationDecision(false));
        }

        return Mono.just(new AuthorizationDecision(true));
    }
}

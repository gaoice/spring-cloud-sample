package com.gaoice.cloud.nebula.gateway.config;

import com.gaoice.cloud.nebula.gateway.component.NebulaReactiveAuthorizationManager;
import com.gaoice.cloud.nebula.gateway.component.NebulaServerAccessDeniedHandler;
import com.gaoice.cloud.nebula.gateway.component.NebulaServerAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author gaoice
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    private final NebulaReactiveAuthorizationManager reactiveAuthorizationManager;
    private final NebulaServerAccessDeniedHandler accessDeniedHandler;
    private final NebulaServerAuthenticationEntryPoint authenticationEntryPoint;
    private final AppProperties appProperties;
    private final Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtGrantedAuthoritiesConverter;

    private final OAuth2ResourceServerProperties resourceServerProperties;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(jwtGrantedAuthoritiesConverter)
                .jwkSetUri(resourceServerProperties.getJwt().getJwkSetUri())
                .and().authenticationEntryPoint(authenticationEntryPoint);
        // 关闭 csrf
        http.csrf().disable();
        // 白名单
        List<String> publicUrls = appProperties.getPublicUrls();
        if (!CollectionUtils.isEmpty(publicUrls)) {
            http.authorizeExchange().pathMatchers(publicUrls.toArray(new String[0])).permitAll();
        }

        http.authorizeExchange()
                .anyExchange().access(reactiveAuthorizationManager)
                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
        return http.build();
    }
}

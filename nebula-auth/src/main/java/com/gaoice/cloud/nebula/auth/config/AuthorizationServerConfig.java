package com.gaoice.cloud.nebula.auth.config;

import com.gaoice.cloud.nebula.auth.bean.Client;
import com.gaoice.cloud.nebula.auth.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Arrays;
import java.util.List;

/**
 * @author gaoice
 */
@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AppProperties appProperties;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final AccessTokenConverter accessTokenConverter;

    private final TokenStore tokenStore;

    private final List<TokenEnhancer> tokenEnhancers;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(tokenEnhancers);

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter)
                .tokenStore(tokenStore)
                .tokenEnhancer(enhancerChain);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        List<Client> clientDefinitions = appProperties.getClients();
        for (Client cd : clientDefinitions) {
            ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder clientBuilder =
                    builder.withClient(cd.getClientId())
                            .secret(passwordEncoder.encode(cd.getSecret()))
                            .scopes(splitToArray(cd.getScopes()))
                            .authorizedGrantTypes(splitToArray(cd.getAuthorizedGrantTypes()))
                            .autoApprove(cd.getAutoApprove())
                            .accessTokenValiditySeconds(cd.getAccessTokenValiditySeconds())
                            .refreshTokenValiditySeconds(cd.getRefreshTokenValiditySeconds());
            // resourceIds 为空则拥有全部资源权限
            if (cd.getResourceIds() != null) {
                clientBuilder.resourceIds(splitToArray(cd.getResourceIds()));
            }
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    public String[] splitToArray(String str) {
        return Arrays.stream(str.split(",")).map(s -> s.replaceAll(" ", "")).toArray(String[]::new);
    }
}

package com.gaoice.cloud.nebula.auth.service.impl;

import com.gaoice.cloud.nebula.auth.bean.NebulaUser;
import com.gaoice.cloud.nebula.auth.config.AppContext;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gaoice
 */
@AllArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final RestTemplate restTemplate;

    private final HttpServletRequest request;

    private final AppContext appContext;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        String url = String.format(appContext.getUrl(clientId), username);

        NebulaUser user = restTemplate.getForObject(url, NebulaUser.class);
        if (user == null) {
            LOGGER.error("username:{},url:{},查询结果为空！", username, url);
            throw new UsernameNotFoundException("NebulaUser 不能为空");
        }
        user.setClientId(clientId);

        return user;
    }
}

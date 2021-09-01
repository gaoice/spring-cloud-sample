package com.gaoice.cloud.nebula.auth.bean;

import lombok.Data;

/**
 * @author gaoice
 */
@Data
public class Client {

    private String clientId;

    private String secret = "secret";

    private String scopes = "all";

    private String authorizedGrantTypes = "authorization_code, password, client_credentials, implicit, refresh_token";

    private String resourceIds = null;

    private Integer accessTokenValiditySeconds = 3600 * 24;

    private Integer refreshTokenValiditySeconds = 3600 * 24 * 7;

    private Boolean autoApprove = true;

    /**
     * client 对应的可以通过 username 查询对应用户的 url
     * 比如：商城系统中，有 admin 的账户体系和普通 consumer 的账户体系，可以为 admin 和 consumer 配置不同的 userServiceUrl
     * 默认使用 n-s-user
     */
    private String userServiceUrl = "http://n-s-user/user/username/%s";
}

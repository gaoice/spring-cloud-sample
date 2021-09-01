package com.gaoice.cloud.nebula.auth.config;

import com.gaoice.cloud.nebula.auth.bean.Client;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gaoice
 */
@Data
@EnableConfigurationProperties
@ConfigurationProperties("nebula-auth")
@Component
public class AppProperties {

    private List<Client> clients;

    private String signingKey = "nebula-auth-default-signing-key";

    private String jksPath = "nebula.jks";

    private String jksAlias = "nebula";

    private String jksStorePass = "nebula";

    private String jksKeyPass = "nebula";
}

package com.gaoice.cloud.nebula.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author gaoice
 */
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private KeyPair keyPair;

    /**
     * jwk-set-uri
     */
    @GetMapping("/publicKey")
    public Map<String, Object> publicKey() {
        RSAKey key = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()).build();
        return new JWKSet(key).toJSONObject();
    }
}

package com.gaoice.cloud.nebula.gateway.util;

import com.nimbusds.jose.JWSObject;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import java.text.ParseException;

/**
 * @author gaoice
 */
public class TokenUtils {

    public static String getToken(ServerWebExchange exchange) {
        return getToken(exchange.getRequest());
    }

    public static String getToken(ServerHttpRequest request) {
        return request.getHeaders().getFirst("Authorization");
    }

    public static String getPayload(String token) throws ParseException {
        token = token.replace("Bearer ", "");
        JWSObject jwsObject = JWSObject.parse(token);
        return jwsObject.getPayload().toString();
    }
}

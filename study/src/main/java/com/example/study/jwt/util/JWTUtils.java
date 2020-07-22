package com.example.study.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.study.jwt.entity.JWTEntity;
import com.example.study.test.entity.Person;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author hjs
 * @date 2020/7/17
 * @description
 */
public class JWTUtils {
    public static Map<String, String> getTokens(Person person) {
        Map<String, String> resultMap = new HashMap<>();
        String refreshToken = getToken(new JWTEntity(JWTEntity.REFRESH_TOKEN_SUBJECT, person), false);
        String accessToken = getAccessTokenByRefreshToken(refreshToken);
        resultMap.put(JWTEntity.ACCESS_TOKEN_SUBJECT, accessToken);
        resultMap.put(JWTEntity.REFRESH_TOKEN_SUBJECT, refreshToken);
        return resultMap;
    }

    /**
     * @param jwtEntity
     * @param refresh   是否通过refreshToken 来生成 accessToken
     * @return token string
     */
    private static String getToken(JWTEntity jwtEntity, boolean refresh) {
        if (refresh) {
            jwtEntity.setJwtClaimMap(null);
            jwtEntity.setTokenSubject(JWTEntity.ACCESS_TOKEN_SUBJECT);
        }
        JWTCreator.Builder builder = JWT.create()
                .withHeader(getJwtHeader()) //设置头部信息 Header
                /*设置 载荷 Payload*/
                .withIssuer(JWTEntity.MOCK_ISSUER) //签名是有谁生成 例如 服务器
                .withSubject(jwtEntity.getTokenSubject()) //签名的主题
                .withAudience(jwtEntity.getUserId()) //签名的观众 也可以理解谁接受签名的
                .withNotBefore(jwtEntity.getTokenStartDate()) //定义在什么时间之前，该jwt都是不可用的.
                .withIssuedAt(new Date()) //生成签名的时间
                .withExpiresAt(jwtEntity.getTokenEndDate()); //签名过期的时间

        Map<String, Claim> claimMap = jwtEntity.getJwtClaimMap();
        Set<String> keySet = claimMap.keySet();
        for (String key : keySet) {
            builder.withClaim(key, claimMap.get(key).asString());
        }

        return builder.sign(Algorithm.HMAC256(jwtEntity.getSecret()));
    }

    public static String getAccessTokenByRefreshToken(@NotNull String refreshToken) {
        JWTEntity jwtEntity = parseToken(refreshToken);
        return getToken(jwtEntity, true);
    }

    private static JWTEntity parseToken(@NotNull String token) {
        String secret = JWTEntity.DEFAULT_SECRET;
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(JWTEntity.MOCK_ISSUER).build(); //Reusable verifier instance
        DecodedJWT decodedJWT = verifier.verify(token);
        JWTEntity jwtEntity = new JWTEntity(decodedJWT.getSubject());
        if (!Objects.isNull(decodedJWT) && decodedJWT.getAudience().size() > 0) {
            jwtEntity.setUserId(decodedJWT.getAudience().get(0));
        }
        jwtEntity.setIssuer(decodedJWT.getIssuer());
        jwtEntity.setJwtClaimMap(decodedJWT.getClaims());
        jwtEntity.setTokenStartDate(decodedJWT.getNotBefore());
        jwtEntity.setTokenEndDate(decodedJWT.getExpiresAt());
        jwtEntity.setSecret(secret);
        return jwtEntity;
    }

    /**
     * JWT:头部
     *
     * @return
     */
    private static Map<String, Object> getJwtHeader() {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return map;
    }
}

package com.chxyz.demo.authentication;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class TokenUtils {
    // 版本
    public static String TOKEN_VERSION = "1";
    // 设置发行人
    public static String ISSUER = "zhicall";
    // 设置抽象主题
    public static String SUBJECT = "subject";

    // HS256 私钥
    public static String HS256KEY = "xxxxxx";

    public static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public static Key signingKey = new SecretKeySpec(Base64.decodeBase64(HS256KEY), signatureAlgorithm.getJcaName());

    public static String getJWTString(String login, Map<String, Object> claims) {

        long nowMillis = System.currentTimeMillis();
        claims.put(Claims.ID, TOKEN_VERSION);
        claims.put(Claims.ISSUER, ISSUER);
        claims.put(Claims.SUBJECT, SUBJECT);
        claims.put(Claims.AUDIENCE, login);
        claims.put(Claims.EXPIRATION, new Date(nowMillis + (Cst.TOKEN_TIMEOUT_MIN * 60 * 1000)));
        claims.put(Claims.ISSUED_AT, new Date(nowMillis));

        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims);
        //System.out.println(System.currentTimeMillis() - nowMillis);
        jwtBuilder.signWith(signatureAlgorithm, signingKey);
        return jwtBuilder.compact();
    }

    public static boolean isValid(String token) {
        try {
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token.trim());
            Long exp = (Long) jwsClaims.getBody().get(Claims.EXPIRATION);
            //System.out.println(exp - System.currentTimeMillis());
            return exp - System.currentTimeMillis() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Object> parseJWTtoMap(String token) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token.trim()).getBody();
        return claims;
    }

    public static String getHS512Key() {
        Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);
        String keyStr = Base64.encodeBase64String(key.getEncoded());
        return keyStr;
    }
}

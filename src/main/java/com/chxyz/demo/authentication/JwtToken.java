package com.chxyz.demo.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

public class JwtToken {
    public static String createToken() throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256"); // 声明加密的算法 通常直接使用 HMAC SHA256
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)//header
                .withClaim("name", "zwz")//payload
                .withClaim("age", "18")
                .sign(Algorithm.HMAC256("secret"));//加密
        return token;
    }

    // 验证代码
    public static void verifyToken(String token,String key) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key))
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        System.out.println(claims.get("name").asString());
    }
}

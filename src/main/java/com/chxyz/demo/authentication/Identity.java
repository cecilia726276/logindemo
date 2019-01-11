package com.chxyz.demo.authentication;



import lombok.Getter;
import lombok.Setter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Setter
@Getter
public class Identity {
    private String token; //token序列
    private String id; // 对应user的id
    private String issuer; //签发者
    private String role; // 角色
    private String userName; //用户名
    private Long duration; // 有效时长，单位毫秒


    public static String createToken(Identity identity, String apiKeySecret) {
        //JWT采用的签名算法

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //获取当前时间戳
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //封装好加密算法与私钥apiKeySecret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKeySecret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //采用建造者模式定制化token属性
        JwtBuilder builder = Jwts.builder().setId(identity.getId())
                .setIssuedAt(now)
                .setSubject(identity.getId() + "," + identity.getUserName() + "," + identity.getRole())
                .setIssuer(identity.getIssuer())
                .signWith(signatureAlgorithm, signingKey);
        //设置失效时间戳
        long ttlMillis = identity.getDuration();
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
            identity.setDuration(exp.getTime());
        }
        // 生成token并序列化编码成一个URL安全的字符串
        // log.info("token生成成功");
        return builder.compact();
    }

}

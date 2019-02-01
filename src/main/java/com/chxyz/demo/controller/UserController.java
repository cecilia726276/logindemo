package com.chxyz.demo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chxyz.demo.model.MessageDO;
import com.chxyz.demo.model.UserDO;
import com.chxyz.demo.service.MessageService;
import com.chxyz.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    static final long maxAge = 30L * 24L * 3600L * 1000L;

    @Autowired
    private MessageService messageService;

//    //DecodedJWT jwt = JWT.decode(token);
//    String algorithm = jwt.getAlgorithm();
//    String type = jwt.getType();
//    String contentType = jwt.getContentType();
//    String keyId = jwt.getKeyId();
//    Claim claim = jwt.getHeaderClaim("owner");
//    Map<String, Claim> claims = jwt.getClaims();    //Key is the Claim name
//    Claim claim = claims.get("isAdmin");
//    or
//    Claim claim = jwt.getClaim("isAdmin");

    @RequestMapping(value = "authentication", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData validateToken(@RequestParam String token) {
        boolean outcome = validate(token);
        if (!outcome){
           return ResponseData.customerError().putDataValue("ERRORS", new String[]{"Token expired!"});
        }
        return ResponseData.ok();
    }

    private boolean validate(String token){
        log.warn("Receive token, PARAMETER:{}",token);
        DecodedJWT jwt = JWT.decode(token);
        log.warn("Decoding");

        Date expiresAt = jwt.getExpiresAt();
        Date current = new Date();
        boolean expired = current.after(expiresAt);
        if (expired){
            log.warn("Token expired.");
            return false;
        }
        log.warn("Pass validation.");
        return true;
    }


    private String encryption(String id, Long maxAge, String secret) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256"); // 声明加密的算法 通常直接使用 HMAC SHA256
        map.put("typ", "JWT");
       //map.put("exp", System.currentTimeMillis() + maxAge);
        String token = JWT.create()
                .withHeader(map)
                .withExpiresAt(new Date(new Date().getTime() + maxAge))
                .withClaim("id", id)
                .sign(Algorithm.HMAC256(secret));
        return token;
    }

    private String mdEncryption(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] pwdByteArray = password.getBytes("utf-8");
        byte[] mdByteArray=md.digest(pwdByteArray);
        StringBuffer hexValue=new StringBuffer();
        for(int i=0;i<mdByteArray.length;i++){
            int val=((int)mdByteArray[i])&0xff;
            if(val<16){
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    // 之后需定义并返回一个Result
    public ResponseData login(@RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(username + "; " + password);
        // 调用认证服务进行用户名密码认证，如果认证通过，Login Action层调用用户信息服务获取用户信息
        // 返回用户信息后，Login Action从配置文件中获取Token签名生成的秘钥信息，进行Token的生成
        // 完成JWT数据签名后，将其设置到COOKIE对象中，并重定向到首页，完成登录过程
        UserDO user = userService.queryUserByName(username);
        if (user == null){
            return ResponseData.customerError().putDataValue("ERRORS",new String[]{"用户不存在"});
        }else if (user.getUserName().equals(username) && (mdEncryption(password)).equals(user.getPassword())){
            ResponseData responseData = ResponseData.ok();
            long maxAge = 24L * 3600L * 1000L; // 1 day
            String secret = "secret";
            String token = encryption(user.getId(), maxAge, secret );
            user.setLatestToken(token);
            log.warn("生成token",token);
            Boolean outcome = userService.updateUser(user);
            if (outcome){
                responseData.putDataValue("token", token);
                return responseData;
            }
            return ResponseData.serverInternalError().putDataValue("ERRORS",new String[]{"服务器内部错误"});
        }
        return ResponseData.customerError().putDataValue("ERRORS", new String[]{"用户名或者密码错误"});
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData register(@RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(username + "; " + password);
        UserDO user = userService.queryUserByName(username);
        if (user == null){
            String uuid = UUID.randomUUID().toString();	//获取UUID并转化为String对象
            uuid = uuid.replace("-", "");
            password= mdEncryption(password);
            UserDO newUser = new UserDO(uuid,username,password);
            long maxAge = 30L * 24L * 3600L * 1000L;
            String secret = "secret";
            String token = encryption(uuid, maxAge, secret);
            newUser.setLatestToken(token);
            Boolean outcome = userService.insertUser(newUser);
            if (outcome){
                ResponseData responseData = ResponseData.ok();
                responseData.putDataValue("token", token);
                return responseData;
            }
        }
        return ResponseData.customerError().putDataValue("ERRORS",new String[]{"用户已存在"});

    }

    @RequestMapping(value = "requestMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData requestMessage(@RequestParam String token){
        boolean valid = validate(token);
        if (!valid){
            return ResponseData.customerError().putDataValue("ERRORS",new String[]{"用户登陆已超时"});
        }
        try {
            List<MessageDO> messageDOList = messageService.queryAllMessage();
            return ResponseData.ok().putDataValue("list",messageDOList);
        } catch (Exception e){
            return ResponseData.serverInternalError().putDataValue("ERRORS",new String[]{"服务器发送未知错误！"});
        }
    }


    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData sendMessage(@RequestParam String token, @RequestParam String content) throws UnsupportedEncodingException {
        boolean valid = validate(token);
        DecodedJWT jwt = JWT.decode(token);
        Claim claim = jwt.getClaim("id");
        String id = claim.asString();
        System.out.println(claim.asString());
        MessageDO messageDO = new MessageDO();
        messageDO.setContent(content);
        messageDO.setUid(id);
        boolean outcome = messageService.insertMessage(messageDO);
        if (outcome){
            ResponseData responseData = ResponseData.ok();
        }
        return ResponseData.ok();
    }


}

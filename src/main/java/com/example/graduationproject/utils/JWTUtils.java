package com.example.graduationproject.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static final String SIGN="!qwe@[wef";//后面从数据库中获取
    /**
     * 生成token header.payload.sign
     * 由于header有默认值，所以我们可以不需要设置，如果有需要可以进行设置
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);//默认7天过期
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //playload
        map.forEach((k,v)->{builder.withClaim(k,v);});

        String token = builder.withExpiresAt(instance.getTime())//令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));//签名

        return token;
    }

    /**
     * 验证token合法性,并返回DecodedJWT对象
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
//    /**
//     * 获取token信息方法
//     */
//    public static DecodedJWT getTokenInfo(String token){
//        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
//        return verify;
//    }
}

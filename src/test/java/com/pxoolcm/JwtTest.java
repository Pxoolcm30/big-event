package com.pxoolcm;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/7 17:18
 */
public class JwtTest {

    @Test
    public void testGun(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        String token = JWT.create()
                .withClaim("user",claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*3))//指定过期时间
                .sign(Algorithm.HMAC256("pxoolcm"));//指定算法，设置密钥
        System.out.println(token);
    }

    @Test
    public void testParse(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MjU3MTE4NTd9" +
                ".pXqcZBf4_9tVLY9MEJEHTcJhk6n31F0f_2lRlu9JpWc";
        JWTVerifier pxoolcm = JWT.require(Algorithm.HMAC256("pxoolcm")).build();//得到JWT验证器

        DecodedJWT decodedJWT = pxoolcm.verify(token);//JWT验证后得到JWT对象
        Map<String, Claim> claims = decodedJWT.getClaims();//JWT对象可以得到头部和有效载荷，还有数字签名
        Claim user = claims.get("user");
        System.out.println(user);


        //头部被篡改，验证失败
        //有效载荷被篡改，验证失败
        //密钥被篡改，验证失败
        //token过期了，验证失败
    }

}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-07  17:18
 * @Description: TODO
 * @Version: 1.0
 */

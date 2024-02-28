package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class JwtTokenUtil {

    // 用户名的key
    private static final String CLAIM_KEY_USERNAME = "sub";
    // jwt创建时间
    private static final String CLAIM_KEY_CREATED = "created";

    // JWT秘钥
    @Value("${jwt.secret}")
    private String secret;

    // JWT有效期（单位：秒）
    @Value("${jwt.expiration}")
    private Long expiration; // 1天

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 根据荷载生成JWT Token
     */
    public String generateToken(Map<String, Object> claims) {
        System.out.println("secret" + secret);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate()) // 失效时间
                .signWith(SignatureAlgorithm.HS512, secret) // 签名
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}",token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username =  claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

//    /**
//     * 根据用户信息生成token
//     */
//    public String generateToken(LoginUser loginUser) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(CLAIM_KEY_USERNAME, loginUser.getUsername());
//        claims.put(CLAIM_KEY_CREATED, new Date());
//        return generateToken(claims);
//    }
//
//    /**
//     * 判断token是否可以被刷新
//     */
//    public boolean canRefresh(String token) {
//        return !isTokenExpired(token);
//    }
//
//    /**
//     * 刷新token
//     */
//    public String refreshToken(String token) {
//        Claims claims = getClaimsFromToken(token);
//        claims.put(CLAIM_KEY_CREATED, new Date());
//        return generateToken(claims);
//    }
//
//    /**
//     * 获取请求token
//     *
//     * @param request
//     * @return token
//     */
//    public String getToken(HttpServletRequest request)
//    {
//        return request.getHeader(header);
//    }

}

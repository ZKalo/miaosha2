package com.kalo.util;

import com.kalo.entity.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Kalo
 * @create 2020-04-21 23:05
 */
public class JwtUtil {

    public static final String SUBJECT = "admin";

    /**
     * 过期时间，毫秒，一周
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    /**
     * 秘钥
     */
    public static final String APPSECRET = "kalo";

    public static final String JWT_KEY ="tqWLNWgqtbc";

    /**
     * 根据用户信息获取对应的token
     * @param model
     * @return
     */
    public static String getUserToken(UserModel model) {
        if (model == null) {
            return null;
        }
        if (model.getTelphone() == null || model.getName() == null || model.getGender() == null
                || model.getAge() == null) {
            return null;
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("name", model.getName())
                .claim("gender", model.getGender())
                .claim("age", model.getAge())
                .claim("telphone", model.getTelphone())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();

        return token;
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {

        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET).
                    parseClaimsJws(token).getBody();
            return claims;

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 获取cookie
     * @param request
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (JWT_KEY.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }
}

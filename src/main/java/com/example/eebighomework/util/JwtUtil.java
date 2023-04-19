package com.example.eebighomework.util;

import java.util.Date;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private static String SECRET_KEY;

    @Value("${jwt.secret}")
    public void setSecretKey(String secret) {
        JwtUtil.SECRET_KEY = secret;
    }

    /**
     * 生成 JWT token
     * @param userId 用户 ID
     * @return JWT token
     */
    public static String generateToken(String userId) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 3600000);
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 从 JWT token 中获取用户 ID
     * @param token JWT token
     * @return 用户 ID
     */
    public static String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * 验证 JWT token 是否有效
     * @param token JWT token
     * @return true 如果 JWT token 有效，否则 false
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty");
        }
        return false;
    }
}


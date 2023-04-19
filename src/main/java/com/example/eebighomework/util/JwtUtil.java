package com.example.eebighomework.util;

import java.util.Date;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

public class JwtUtil {
    private static String SECRET_KEY;

    @Value("${jwt.secret}")
    public void setSecretKey(String secret) {
        JwtUtil.SECRET_KEY = secret;
    }

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

    public static String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

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

package com.springbootblog.springbootrestapi.security;

import com.springbootblog.springbootrestapi.exceptions.BlogApiException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
        return token;
    }

    //obten el usuario desde el token
    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    //validate JWT token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SecurityException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Ivalid JWT Signature");
        }catch (MalformedJwtException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Ivalid JWT token");
        }catch(ExpiredJwtException ex){
            throw  new BlogApiException(HttpStatus.BAD_REQUEST,"Expired JWT token");
        }catch(UnsupportedJwtException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsoported JWT token");
        }catch(IllegalArgumentException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"JWT claims string is empty.");
        }
    }
}

package com.infogalaxy.workshopbookstoreapp.security;

import com.infogalaxy.workshopbookstoreapp.exception.UserAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * The class for creating and decoding JWT Token for User Credentials
 * @author InfoGalaxy
 * @version 1.0.0
 * @created 19-05-2022
 */
@Component
public class JWTTokenUtil {
    private static final String SECRET_KEY = "abcdefghijklmnopqrstuvwxyz1234567890";
    //Validity of Token for 1 Day
    private static final long VALIDITY_IN_MS = 24 * 60 * 60 * 1000;

    public String createToken(String username,String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth",role);

        Date now = new Date();
        Date validity = new Date(now.getTime()+VALIDITY_IN_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isVerifiedUser(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new UserAuthenticationException("JWT Token is Invalid Or Expired.", HttpStatus.UNAUTHORIZED);
        }
    }
}

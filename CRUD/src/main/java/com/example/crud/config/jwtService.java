package com.example.crud.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class jwtService {

    private static final String SECRET_KEY = "qexuWfPmWzw0UMlGnG2cfMzgT617aGi7uFbOyDtNOWXkusx5vHCppTgMtR946Xz9IE9e7ILuBve44Y5MK8EnMoP5tAw0nZE3BxXo8IT1W7wgIaRMaFhUD5JR8tkd5x1al28ZoWLFUx43povw9PdASPxjBvwmCS8ztvUam0U3XIlVXjuXNipvKRM9bKV3lJlndBSviNp4y8ENcNwfwJlBNh9kjr2VFQ26yF2SHBc963AE7ZYrPYkpj6DPnUxwSLGV\n";


    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 60)).signWith(getSingInKey(), SignatureAlgorithm.HS256).compact();
    }

    public String getUserName(String token) {
        return getClaim(token, Claims::getSubject);

    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSingInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}

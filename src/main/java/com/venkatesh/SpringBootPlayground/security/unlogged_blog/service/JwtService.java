package com.venkatesh.SpringBootPlayground.security.unlogged_blog.service;

import com.venkatesh.SpringBootPlayground.security.unlogged_blog.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtService {
//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        byte[] keys = new byte[48];
//        SecureRandom.getInstanceStrong().nextBytes(keys);
//        String secretKey = Base64.getEncoder().encodeToString(keys);
//        System.out.println(secretKey);
//        System.out.println(secretKey.length());
//    }

    private static final String SECRET = "4oS6CjDqGdY89cQBgBURgDNgZMcPnEigl+vfqCOphRJxC+uCIxwBjOOe0GBxT1Lv";

    public String generateToken(String userName, Role role){
        Map<String, String> claims = new HashMap<>();
        claims.put("role", role.name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        // Decode the base64 encoded secret key and return a Key object
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extracts the userName from the JWT token.
    //return -> The userName contained in the token.
    public String extractUserName(String token) {
        // Extract and return the subject claim from the token
        return extractClaim(token, Claims::getSubject);
    }


    // Extracts the expiration date from the JWT token.
    //@return The expiration date of the token.
    public Date extractExpiration(String token) {
        // Extract and return the expiration claim from the token
        return extractClaim(token, Claims::getExpiration);
    }


    // Extracts a specific claim from the JWT token.
    // claimResolver A function to extract the claim.
    // return-> The value of the specified claim.
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        // Extract the specified claim using the provided function
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    //Extracts all claims from the JWT token.
    //return-> Claims object containing all claims.
    public Claims extractAllClaims(String token) {
        // Parse and return all claims from the token
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build().parseClaimsJws(token).getBody();
    }


    //Checks if the JWT token is expired.
    //return-> True if the token is expired, false otherwise.
    public Boolean isTokenExpired(String token) {
        // Check if the token's expiration time is before the current time
        return extractExpiration(token).before(new Date());
    }

    //Validates the JWT token against the UserDetails.
    //return-> True if the token is valid, false otherwise.

    public Boolean validateToken(String token, UserDetails userDetails) {
        // Extract username from token and check if it matches UserDetails' username
        final String userName = extractUserName(token);
        // Also check if the token is expired
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

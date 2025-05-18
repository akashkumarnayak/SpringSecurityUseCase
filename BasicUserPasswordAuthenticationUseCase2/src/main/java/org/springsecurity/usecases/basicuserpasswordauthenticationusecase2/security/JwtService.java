package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "v0CBrvocO2i30xZV5ZWKe6STWn/KrXs3TuHY3eS699Y=";

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().claims()
                .add(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .and()
                .signWith(getSigningKey())
                .compact();

    }

    public String extractUsername(String authToken) {
        return extractClaim(authToken, Claims::getSubject);
    }

    public Date extractExpiration(String authToken) {
        return extractClaim(authToken, Claims::getExpiration);
    }

    public boolean isExpired(String authToken) {
        return extractExpiration(authToken).before(new Date());
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {

        String dbExtractedUsername = userDetails.getUsername();
        String jwtExtractedUsername = extractUsername(authToken);

        return dbExtractedUsername.equals(jwtExtractedUsername) && !isExpired(authToken);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

//package com.hvv.agriservice.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtUtils {
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private Long expiration;
//
//    @Value("${jwt.refresh.expiration}")
//    private Long refreshExpiration;
//
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userDetails.getUsername());
//    }
//
//    /**
//     * Ham tao moi token
//     * @param claims: quyen gan voi moi token
//     * @param subject: username
//     * @return token duoc tra ve voi nhung thong tin dua vao
//     */
//    private String createToken(Map<String, Object> claims, String subject) {
//        Date current = new Date();
//        Date expiryDate = new Date(current.getTime() + (expiration * 1000));
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(current)
//                .setExpiration(expiryDate)
//                .signWith(getSecretKey())
//                .compact();
//    }
//
//    private Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return  (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    /**
//     * Lay thong tin username tu token
//     * @param token : token chua thong tin username can lay
//     * @return tra ve thong tin username của token
//     */
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    /**
//     * Ham thuc hien lay thoi gian het han cua token
//     * @param token: token chua thong tin thoi gian het han can lay
//     * @return tra ve thong tin thoi gian het han kieu Date
//     */
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    /**
//     * Ham lay thong tin tu token theo truong thong tin dua vao
//     * @param token: token chua thong tin
//     * @param claimsResolver: truong thong tin truyen duoc dang lambda can lay
//     * @return tra ve thong tin truong du lieu can lay tuong ung truyen vao
//     * @param <T>
//     */
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    /**
//     * Ham lay thong tin Claims chua thong tin co trong token
//     * @param token: token truyen vao can lay thong tin
//     * @return tra ve doi tuong Claims chua thong tin token
//     */
//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSecretKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    /**
//     * Ham thong tin lay secretKey
//     * @return : tra ve thong tin Secret key da duoc ma hoa
//     */
//    private SecretKey getSecretKey() {
//        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//    }
//
//    /**
//     * Ham thuc hien kiem tra xem token con han hay khong
//     * @param token: token can kiem tra con han hay khong
//     * @return tra ve xem token con han hay khong
//     */
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//}

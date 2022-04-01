package com.revature.service;

import com.revature.model.User;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JWTService {

    private Key key;

    private static JWTService instance;

    public JWTService(){
    byte[] secret ="sdafsdfsdfcdsfczxcdfdsffczxcvsdfsgcvsdaf".getBytes();
        key= Keys.hmacShaKeyFor(secret);
    }

    // Instance initialization block
    // This will run immediately after the constructor
//    {
//        byte[] secret = "my_secret_password".getBytes();
//        key = Keys.hmacShaKeyFor(secret); // Create a key using
//    }

    // method
    public static JWTService getInstance() {
        if (JWTService.instance == null) {
            JWTService.instance = new JWTService();
        }

        return JWTService.instance;
    }

    public String createJWT(User user)
    {
        String jwt= Jwts.builder()
                .setSubject(user.getUserName())
                .claim("UserID",user.getUserID())
                .claim("UserRole", user.getUserRole())
                .signWith(key)
                .compact();
        return jwt;
    }

    public String encryptString(String input) throws NoSuchAlgorithmException {
        //messageDigest works with MD2, MD5,SHA-1,SHA-224,SHA-256
        MessageDigest md= MessageDigest.getInstance("MD5");
        byte[] messageDigest= md.digest(input.getBytes());
        BigInteger bigInteger= new BigInteger(1,messageDigest);
        return bigInteger.toString(16);
    }

    // Validating a JWT with the key
    public Jws<Claims> parseJwt(String jwt) {
        try {
            Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            //Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

            return token;
        } catch(JwtException e) {
            e.printStackTrace();
            throw new UnauthorizedResponse("JWT was invalid");
        }

    }



}

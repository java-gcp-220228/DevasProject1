package com.revature.service;

import com.revature.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JWTServiceTest {
    private JWTService jwtService= new JWTService();
    @Test
    public void createJWTTest()
    {
        User user= new User(1,"Sarvesh777","Sarvesh","Dev","sarvesh@sarvesh.com","Manager","password");
        Assertions.assertNotNull(jwtService.createJWT(user));

    }

    @Test
    public void encryptStringTest() throws NoSuchAlgorithmException {
        String input="password";
        MessageDigest md= MessageDigest.getInstance("MD5");
        byte[] messageDigest= md.digest(input.getBytes());
        BigInteger bigInteger= new BigInteger(1,messageDigest);
        String expectedValue= bigInteger.toString(16);
        Assertions.assertEquals(expectedValue, jwtService.encryptString(input));
    }


}

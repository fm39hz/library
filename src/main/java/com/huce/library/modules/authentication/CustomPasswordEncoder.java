package com.huce.library.modules.authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomPasswordEncoder extends BCryptPasswordEncoder {
    private static CustomPasswordEncoder instance;
    public CustomPasswordEncoder() {
    }
    public static CustomPasswordEncoder getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (CustomPasswordEncoder.class) {
            if (instance == null) {
                instance = new CustomPasswordEncoder();
            }
        }
        return instance;
    }
    public  String encodePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedPassword = md.digest(password.getBytes());
            return bytesToHex(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encoding password.", e);
        }
    }

    public   boolean matches(String raw, String hasPassword){

        System.out.println("encode:"+ encodePassword(raw));
        System.out.println("hasPassword:" + hasPassword);
        return encodePassword(raw).equals(hasPassword);
    }
    public  String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
package com.example.authserverdemo.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.Callable;

public class GenerateCodeUtil {

    private GenerateCodeUtil() {}

    public static String generateCode() {
        String code = "";

        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            int c=random.nextInt(9000) + 1000;
            code += c;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errors in generating code.");
        }

        return code;
    }
}

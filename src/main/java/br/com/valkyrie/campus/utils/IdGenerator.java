package br.com.valkyrie.campus.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class IdGenerator {
    private static final int FIXED_SUFFIX_LENGTH = 7;

    public static String answerIdGenerator() {
        String prefix = "ANS";
        StringBuilder suffix = new StringBuilder();
        Random random = new SecureRandom();


        for (int i = 0; i < FIXED_SUFFIX_LENGTH; i++) {
            suffix.append(random.nextInt(10));
        }
        return prefix + suffix;
    }
}

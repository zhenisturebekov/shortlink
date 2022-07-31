package com.codereview.linkgenerator.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomTokenGenerator {
    private final static int LEFT_LIMIT_CHARACTER = 97;
    private final static int RIGHT_LIMIT_CHARACTER = 122;
    private final static int LEFT_LIMIT_NUMBER = 48;
    private final static int RIGHT_LIMIT_NUMBER = 57;
    private final static int TARGET_STRING_LENGTH = 6;

    public String generateToken() {
        final Random random = new Random();

        StringBuilder sb = new StringBuilder(TARGET_STRING_LENGTH);
        for (int i = 0; i < TARGET_STRING_LENGTH; i++) {
            int carOrNumber = (int) (1 + Math.random() * 3);

            int randomLimitedInt = (carOrNumber != 1) ?
                    (LEFT_LIMIT_CHARACTER + (int) (random.nextFloat() *
                            (RIGHT_LIMIT_CHARACTER - LEFT_LIMIT_CHARACTER + 1))) :
                    (LEFT_LIMIT_NUMBER + (int) (random.nextFloat() *
                            (RIGHT_LIMIT_NUMBER - LEFT_LIMIT_NUMBER + 1)));

            sb.append((char) randomLimitedInt);
        }

        return sb.toString();
    }
}

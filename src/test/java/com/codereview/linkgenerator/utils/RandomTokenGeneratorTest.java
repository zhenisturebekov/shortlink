package com.codereview.linkgenerator.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

class RandomTokenGeneratorTest {


    private final RandomTokenGenerator randomTokenGenerator = new RandomTokenGenerator();


    @Test
    public void generateRandomStringWhereLengthIsSix() {
        Assertions.assertEquals(6, randomTokenGenerator.generateToken().length());
    }

    @Test
    public void random_String_Contains_Characters_In_Different_Case_And_Numbers() {
        Assertions.assertTrue(Pattern.matches("^[\\w\\d]{6}", randomTokenGenerator.generateToken()));
    }

}

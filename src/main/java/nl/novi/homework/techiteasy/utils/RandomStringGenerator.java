package nl.novi.homework.techiteasy.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGenerator {
    public static String random(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}

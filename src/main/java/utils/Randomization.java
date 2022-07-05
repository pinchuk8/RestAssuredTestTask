package utils;

import java.util.Random;

public class Randomization {
    public static String getRandomString(int length) {
        int leftLimit = 97;
        int rightLimit = 122;

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}


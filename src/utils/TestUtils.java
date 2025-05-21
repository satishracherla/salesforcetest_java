package utils;

import java.util.Random;

public class TestUtils {
    public static String generateUniqueAccountName() {
        int random = new Random().nextInt(9000) + 1000;
        return "Account_" + random;
    }

    public static String generateUniqueContactName() {
        int random = new Random().nextInt(9000) + 1000;
        return "Contact_" + random;
    }

    public static String generateUniqueLeadName() {
        int random = new Random().nextInt(9000) + 1000;
        return "Lead_" + random;
    }
}
package com.pratik.payGateBlend.payment.utils;
import java.util.Random;

public class PaymentUtil {

    public static String getCurrentTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String generateFiveDigitNumber() {
        Random random = new Random();
        return String.valueOf(10000 + random.nextInt(90000));
    }

    public static String generateDRN() {
        String timestamp = getCurrentTimestamp();
        String fiveDigitRandom = generateFiveDigitNumber();
        return "GRIPS_" + fiveDigitRandom + timestamp.substring(5);
    }

    public static String generateIdentificationNumber() {
        String timestamp = getCurrentTimestamp();
        String fiveDigitRandom = generateFiveDigitNumber();
        return "ID_" + fiveDigitRandom + timestamp.substring(5);
    }

    public static String generateDPR() {
        String timestamp = getCurrentTimestamp();
        String fiveDigitRandom = generateFiveDigitNumber();
        return fiveDigitRandom + timestamp.substring(3);
    }

}

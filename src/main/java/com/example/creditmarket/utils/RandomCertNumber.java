package com.example.creditmarket.utils;

import lombok.ToString;

import java.util.Random;

@ToString
public class RandomCertNumber {

    private static int NumLength = 5;

    private static final char[] numberTable = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public static String getCertNum() {
        Random random = new Random();
        StringBuilder certNum = new StringBuilder();

        for (int i = 0; i < NumLength; i++) {
            certNum.append(numberTable[random.nextInt(numberTable.length)]);
        }

        return certNum.toString();
    }
}

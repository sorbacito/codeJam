package com.sorbac.adventOfCode.common;

public enum Converter {
    ;
    public static int hexToInteger(String hex) {
        int result = 0;
        boolean isNegative = hex.startsWith("-");
        if (isNegative) {
            hex = hex.substring(1);
        }

        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            int value;
            if (c >= '0' && c <= '9') {
                value = c - '0';
            } else if (c >= 'A' && c <= 'F') {
                value = 10 + c - 'A';
            } else if (c >= 'a' && c <= 'f') {
                value = 10 + c - 'a';
            } else {
                throw new IllegalArgumentException("Invalid hex character: " + c);
            }
            result = 16 * result + value;
        }

        return isNegative ? -result : result;
    }
}

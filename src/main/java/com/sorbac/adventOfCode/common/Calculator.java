package com.sorbac.adventOfCode.common;

public enum Calculator {
    ;

    // Method to find the greatest common divisor
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Method to find the least common multiple
    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    public static long lcm(long... numbers) {
        long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = lcm(result, numbers[i]);
        }
        return result;
    }
}

package com.sorbac.adventOfCode.common;

import java.util.HashSet;
import java.util.Set;

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

    public static Set<Long> findAllDivisors(long number) {
        Set<Long> divisors = new HashSet<>();
        for (long i = 1; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                divisors.add(i);
                if (i != number / i) {
                    divisors.add(number / i);
                }
            }
        }
        return divisors;
    }

    public static Set<Long> primeFactors(long number) {
        Set<Long> factors = new HashSet<>();
        // Divide by 2 until the number is odd
        while (number % 2 == 0) {
            factors.add(2L);
            number /= 2;
        }

        // Number must be odd at this point, so we can skip one element (Note i = i + 2)
        for (long i = 3; i <= Math.sqrt(number); i += 2) {
            // While i divides number, add i and divide number
            while (number % i == 0) {
                factors.add(i);
                number /= i;
            }
        }

        // If number is a prime number greater than 2
        if (number > 2) {
            factors.add(number);
        }

        return factors;
    }
}

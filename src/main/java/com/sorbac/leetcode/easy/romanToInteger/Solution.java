package com.sorbac.leetcode.easy.romanToInteger;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().romanToInt("MCMXCIV"));
    }
    public int romanToInt(String s) {
        int value = 0;
        int current = 0;
        while (current < s.length()) {
            int maxValid = Math.min(current + 2, s.length());
            String curS = s.substring(current, maxValid);
            try {
                value += Roman.valueOf(curS).value;
                current += 2;
            } catch (IllegalArgumentException e) {
                value += Roman.valueOf(String.valueOf(s.charAt(current++))).value;
            }
        }
        return value;
    }

    private enum Roman {
        I(1),
        V(5),
        X(10),
        L(50),
        C(100),
        D(500),
        M(1000),
        IV(4),
        IX(9),
        XL(40),
        XC(90),
        CD(400),
        CM(900)
        ;
        private final int value;

        Roman(int i) {
            value = i;
        }
    }

}

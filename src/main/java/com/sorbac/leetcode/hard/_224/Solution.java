package com.sorbac.leetcode.hard._224;

public class Solution {
    public int calculate(String s) {
        int result = 0;
        int num = 0;
        int sign = 1;
        for(char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num*10 + c - '0';
            } else if (c == '+') {
                result = sign * num;
                num = 0;
                sign = 1;
            } else if (c == '-') {
                result = sign*num;
                //num = -;
                sign = -1;
            } //else if (c == )
        }
        return result;
    }
}

package com.sorbac.leetcode.easy._1544;

public class Solution {
    public String makeGood(String s) {
        int dif = 'a' - 'A';
        StringBuilder good = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (good.length() > 0 && Math.abs(good.charAt(good.length() - 1) -  c) == dif) {
                good.deleteCharAt(good.length() - 1);
            } else {
                good.append(c);
            }
        }
        return good.toString();
    }
}

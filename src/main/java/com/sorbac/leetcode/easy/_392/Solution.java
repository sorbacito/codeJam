package com.sorbac.leetcode.easy._392;

public class Solution {
    public boolean isSubsequence(String s, String t) {
        int tId = 0;
        for (char sChar : s.toCharArray()) {
            boolean found = false;
            while (!found && tId < t.length()) {
                if (sChar == t.charAt(tId++)) {
                    found = true;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
}

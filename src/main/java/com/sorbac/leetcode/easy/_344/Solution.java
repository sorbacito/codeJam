package com.sorbac.leetcode.easy._344;

/**
 * <a href="https://leetcode.com/problems/reverse-string/">https://leetcode.com/problems/reverse-string/</a>
 */
public class Solution {
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length /2 ; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    }
}

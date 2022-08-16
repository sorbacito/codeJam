package com.sorbac.leetcode.easy._387;

import java.util.HashMap;
import java.util.Set;

/**
 * {@link https://leetcode.com/problems/first-unique-character-in-a-string/}
 */
public class Solution {
    public int firstUniqChar(String s) {
        int[] chars = new int['z' - 'a' + 1];
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (chars[c - 'a'] == 0) {
                chars[c - 'a'] = i + 1;
            } else {
                chars[c - 'a'] = s.length() + 1;
            }
        }
        int returnValue = s.length() + 1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 0) {
                returnValue = Math.min(returnValue, chars[i]);
            }
        }
        return returnValue == s.length() + 1 ? -1 : returnValue - 1;
    }
}

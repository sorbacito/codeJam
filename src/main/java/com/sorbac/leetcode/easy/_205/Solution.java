package com.sorbac.leetcode.easy._205;

public class Solution {
    public boolean isIsomorphic(String s, String t) {
        char[] mapST = new char[128];
        char[] mapTS = new char[128];
        for (int i = 0; i < s.length(); i++) {
            int charIdS = s.charAt(i);
            int charIdT = t.charAt(i);
            if ((mapST[charIdS] == '\0' && mapTS[charIdT] == '\0')
                || (mapST[charIdS] == t.charAt(i)
                && mapTS[charIdT] == s.charAt(i))) {
                mapST[charIdS] = t.charAt(i);
                mapTS[charIdT] = s.charAt(i);
            } else {
                return false;
            }
        }
        return true;
    }
}

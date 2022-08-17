package com.sorbac.leetcode.easy._804;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/unique-morse-code-words/">https://leetcode.com/problems/unique-morse-code-words/</a>
 */
public class Solution {
    private final String[] morseAlphabet = new String[] {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
            "-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    public int uniqueMorseRepresentations(String[] words) {
        Map<String, Boolean> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(morseCode(word), Boolean.TRUE);
        }
        return wordMap.keySet().size();
    }

    private String morseCode(String word) {
        StringBuilder wordMorse = new StringBuilder();
        word.chars().forEachOrdered(c -> wordMorse.append(morseAlphabet[c - 'a']));
        return wordMorse.toString();
    }
}

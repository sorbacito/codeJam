package com.sorbac.leetcode.medium._2131;

import java.util.*;

public class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(words).forEach(word -> map.merge(word, 1, (prev, one) -> prev + 1));
        int pairs = 0;
        for (String uWord : new HashSet<>(List.of(words))) {
            String uRev = new StringBuilder(uWord).reverse().toString();
            if (uWord.equals(uRev)) {
                Integer count = map.get(uWord);
                if (pairs % 2 == 1) {
                    count -= count%2;
                }
                pairs += count;
                int toRemove = count;
                map.merge(uWord, 0, (prev, ignored) -> prev - toRemove);
            } else {
                int count1 = Optional.ofNullable(map.get(uWord)).orElse(0);
                int count2 = Optional.ofNullable(map.get(uRev)).orElse(0);
                int count = Math.min(count1, count2);
                pairs += 2*count;
                map.merge(uWord, 0, (prev, ignored) -> prev - count);
                map.merge(uRev, 0, (prev, ignored) -> prev - count);
            }
        }
        return pairs*2;
    }
}

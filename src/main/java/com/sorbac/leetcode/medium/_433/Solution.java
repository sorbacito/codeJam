package com.sorbac.leetcode.medium._433;

import java.util.*;

public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> sBank = new HashSet<>(Arrays.asList(bank));
        List<String> level = new ArrayList<>();
        level.add(start);
        int mutations = 1;
        while (!level.isEmpty()) {
            List<String> newLevel = new ArrayList<>();
            for (String word : level) {
                List<String> validMutation = getValidMutations(word, sBank);
                if (validMutation.contains(end)) {
                    return mutations;
                }
                validMutation.forEach(sBank::remove);
                newLevel.addAll(validMutation);
            }
            mutations++;
            level = newLevel;
        }
        return -1;
    }

    private List<String> getValidMutations(String word, Set<String> sBank) {
        List<String> mutations = new ArrayList<>();
        for (String possibleMutation : sBank) {
            if (isMutation(word, possibleMutation)) {
                mutations.add(possibleMutation);
            }
        }
        return mutations;
    }

    private boolean isMutation(String word, String possibleMutation) {
        int mutations = 0;
        for (int i = 0; i < 8; i++) {
            if (word.charAt(i) != possibleMutation.charAt(i)) {
                mutations++;
            }
            if (mutations > 1) break;
        }
        return mutations == 1;
    }
}

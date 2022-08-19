package com.sorbac.leetcode.medium._659;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <a href="https://leetcode.com/problems/split-array-into-consecutive-subsequences/">https://leetcode.com/problems/split-array-into-consecutive-subsequences/
 * </a>
 */
public class Solution {
    public boolean isPossible(int[] nums) {
        TreeMap<Integer, Integer> valueOccurences = new TreeMap<>();
        for (int num : nums) {
            valueOccurences.merge(num, 1, Integer::sum);
        }
        Map<Integer, Integer> subseqEnds = new HashMap<>();
        while (!valueOccurences.isEmpty()) {
            Integer value = valueOccurences.keySet().iterator().next();
            // Try to append value to some existing subsequence
            if (subseqEnds.containsKey(value - 1)) {
                removeValue(subseqEnds, value - 1);
                subseqEnds.merge(value, 1, Integer::sum);
                removeValue(valueOccurences, value);
            } else { // Try to create new valid subsequence
                if (valueOccurences.containsKey(value + 1) && valueOccurences.containsKey(value + 2)) {
                    removeValue(valueOccurences, value);
                    removeValue(valueOccurences, value + 1);
                    removeValue(valueOccurences, value + 2);
                    subseqEnds.merge(value + 2, 1, Integer::sum);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private static void removeValue(Map<Integer, Integer> intToIntMap, Integer value) {
        intToIntMap.merge(value, -1, Integer::sum);
        if (intToIntMap.get(value) == 0) {
            intToIntMap.remove(value);
        }
    }
}

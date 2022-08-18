package com.sorbac.leetcode.medium._1338;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * <a href="https://leetcode.com/problems/reduce-array-size-to-the-half/">https://leetcode.com/problems/reduce-array-size-to-the-half/</a>
 */
public class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> valueOccurences = new HashMap<>();
        Arrays.stream(arr).forEach(num -> {
            valueOccurences.merge(num, 1, Integer::sum);
        });
        TreeMap<Integer, Set<Integer>> occurenceToValue = new TreeMap<>(Collections.reverseOrder());
        valueOccurences.keySet().stream().forEach(value -> {
            int occurence = valueOccurences.get(value);
            occurenceToValue.putIfAbsent(occurence, new HashSet<>());
            occurenceToValue.get(occurence).add(value);
        });
        int setSize = 0;
        int sum = 0;
        Iterator<Integer> ocToValIt = occurenceToValue.keySet().iterator();
        while (true) {
            Integer occurence = ocToValIt.next();
            for(int i = 0; i < occurenceToValue.get(occurence).size(); i++) {
                sum += occurence;
                setSize++;
                if (2*sum >= arr.length) {
                    return setSize;
                }
            }
        }
    }
}

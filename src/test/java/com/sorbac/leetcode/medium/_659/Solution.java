package com.sorbac.leetcode.medium._659;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
    public boolean isPossible(int[] nums) {
        TreeMap<Integer, TreeSet<Integer>> valueToIndex = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            valueToIndex.putIfAbsent(num, new TreeSet<>(Comparator.reverseOrder()));
            valueToIndex.get(num).add(i);
        }
        while (!valueToIndex.isEmpty()) {
            int value = valueToIndex.keySet().iterator().next();
            int size = 0;
            while (value >= 0 && valueToIndex.containsKey(value)) {
                int valueIdx = valueToIndex.get(value).iterator().next();
                valueToIndex.get(value).remove(valueIdx);
                if (valueToIndex.get(value).isEmpty()) {
                    valueToIndex.remove(value);
                }
                size++;
                value--;
            }
            if (size < 3) {
                return false;
            }
        }
        return true;
    }
}

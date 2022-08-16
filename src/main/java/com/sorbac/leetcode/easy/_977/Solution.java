package com.sorbac.leetcode.easy._977;

public class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] vals = new int[10_000 + 1];
        int[] retVals = new int[nums.length];
        for (int num : nums) {
            int value = Math.abs(num);
            vals[value] = vals[value] + 1;
        }
        int idx = 0;
        for (int i = 0; i < vals.length; i++) {
            for(int j = 0; j < vals[i]; j++) {
                retVals[idx++] = i*i;
            }
        }
        return retVals;
    }
}

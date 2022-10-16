package com.sorbac.leetcode.easy._724;

public class Solution {
    public int pivotIndex(int[] nums) {
        int[] sumToRight = new int[nums.length];
        int sumRight = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            sumToRight[i] = sumRight;
            sumRight += nums[i];
        }
        int sumLeft = 0;
        for(int i = 0; i < nums.length; i++) {
            if (sumLeft == sumToRight[i]) {
                return i;
            }
            sumLeft += nums[i];
        }
        return -1;
    }
}

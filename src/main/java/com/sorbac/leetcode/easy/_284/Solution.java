package com.sorbac.leetcode.easy._284;

public class Solution {
    public void moveZeroes(int[] nums) {
        int firstZero = findFirstZero(nums);
        if (firstZero != -1) {
            int firstNonZero = findFirstNonZero(firstZero, nums);
            while (firstNonZero != -1) {
                nums[firstZero++] = nums[firstNonZero];
                nums[firstNonZero] = 0;
                firstNonZero = findFirstNonZero(firstNonZero, nums);
            }
        }
    }

    private int findFirstNonZero(int firstZero, int[] nums) {
        for (int i = firstZero; i < nums.length; i ++) {
            if (nums[i] != 0) {
                return i;
            }
        }
        return -1;
    }

    private int findFirstZero(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}

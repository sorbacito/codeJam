package com.sorbac.leetcode.medium._189;

public class Solution {
    public void rotate(int[] nums, int k) {
        int reversePoint = nums.length - 1 + (-k % nums.length);
        reverse(nums, 0, reversePoint);
        if (reversePoint < nums.length - 1) {
            reverse(nums, (reversePoint + 1) % nums.length, nums.length - 1);
        }
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        for (int i = 0; i < (end - start + 1)/2; i++) {
            int temp = nums[start + i];
            nums[start + i] = nums[end - i];
            nums[end - i] = temp;
        }
    }
}

package com.sorbac.leetcode.easy._26;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int newIdx = 0;
        int curIdx = 0;
        while (curIdx < nums.length) {
            if (nums[curIdx] != nums[newIdx]) {
                nums[++newIdx] = nums[curIdx];
            }
            curIdx++;
        }
        return newIdx + 1;
    }
}

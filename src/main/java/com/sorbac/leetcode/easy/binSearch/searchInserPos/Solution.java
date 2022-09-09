package com.sorbac.leetcode.easy.binSearch.searchInserPos;

public class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        int current;
        while (left < right) {
            current = (left + right)/2;
            if (nums[current] == target) {
                return current;
            } else {
                if (nums[current] < target) {
                    left = current + 1;
                } else {
                    right = current;
                }
            }
        }
        return left;
    }
}

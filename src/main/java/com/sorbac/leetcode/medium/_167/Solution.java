package com.sorbac.leetcode.medium._167;

/**
 * <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/">https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/</a>
 */
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int firstId = 0;
        int lastId = numbers.length - 1;
        while (true) {
            while (numbers[firstId] + numbers[lastId] > target) {
                lastId--;
            }
            if (numbers[firstId] + numbers[lastId] == target) {
                return new int[]{firstId + 1, lastId + 1};
            }
            while (numbers[firstId] + numbers[lastId] < target) {
                firstId++;
            }
            if (numbers[firstId] + numbers[lastId] == target) {
                return new int[]{firstId + 1, lastId + 1};
            }
        }
    }
}

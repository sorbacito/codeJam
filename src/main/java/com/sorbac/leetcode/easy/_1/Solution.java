package com.sorbac.leetcode.easy._1;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> valueToIdx = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (valueToIdx.containsKey(target - nums[i])) {
                return new int[]{valueToIdx.get(target - nums[i]), i};
            }
            valueToIdx.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}

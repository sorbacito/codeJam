package com.sorbac.leetcode.easy._350;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/intersection-of-two-arrays-ii/">https://leetcode.com/problems/intersection-of-two-arrays-ii/</a>
 */
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums1Count = new HashMap<>();
        Arrays.stream(nums1).forEach(num -> nums1Count.merge(num, 1, Integer::sum ));
        Map<Integer, Integer> nums2Count = new HashMap<>();
        Arrays.stream(nums2).forEach(num -> nums2Count.merge(num, 1, Integer::sum ));
        return nums1Count.keySet().stream().filter(nums2Count::containsKey)
                .map(num -> {
                    int[] mapped = new int[Math.min(nums1Count.get(num), nums2Count.get(num))];
                    Arrays.fill(mapped, num);
                    return mapped;
                }).flatMapToInt(Arrays::stream).toArray();
    }

}

package com.sorbac.leetcode.easy._88;

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] vals = new int[n+m];
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 + idx2 < n + m) {
            if (idx1 < m || nums1[idx1] <= nums2[idx2]) {
                vals[idx1 + idx2] = nums1[idx1++];
            } else {
                vals[idx1 + idx2] = nums2[idx2++];
            }
        }
        System.arraycopy(vals, 0, nums1, 0, vals.length);
    }
}

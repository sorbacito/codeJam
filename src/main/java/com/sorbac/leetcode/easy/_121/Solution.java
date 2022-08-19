package com.sorbac.leetcode.easy._121;

public class Solution {
    public int maxProfit(int[] prices) {
        int[] maxFromDay = new int[prices.length];
        int max = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            maxFromDay[i] = max;
        }
        int maxDiff = 0;
        for (int i = 0; i < prices.length; i++) {
            maxDiff = Math.max(maxDiff, maxFromDay[i] - prices[i]);
        }
        return maxDiff;
    }
}

package com.sorbac.leetcode.hard._871;

/**
 * <a href="https://leetcode.com/problems/minimum-number-of-refueling-stops/submissions/">https://leetcode.com/problems/minimum-number-of-refueling-stops/submissions/</a>
 */
public class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        }
        int[] maxDist = new int[stations.length+1];
        maxDist[0] = startFuel;
        for (int s = 0; s < stations.length; s++) {
            int distance = stations[s][0];
            int fuel = stations[s][1];
            for (int i = s; i >= 0; i--) {
                if (maxDist[i] >= distance) {
                    maxDist[i + 1] = Math.max(maxDist[i + 1], maxDist[i] + fuel);
                }
            }
        }
        for (int i = 0; i < maxDist.length; i++) {
            if (maxDist[i] >= target) {
                return i;
            }
        }
        return -1;
    }
}

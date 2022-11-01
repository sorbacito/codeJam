package com.sorbac.leetcode.medium._1706;

public class Solution {
    public int[] findBall(int[][] grid) {
        int[] balls = new int[grid[0].length];
        for (int col = 0; col < grid[0].length; col++) {
            int pos = col;
            for (int[] ints : grid) {
                if ((ints[pos] == -1 && (pos == 0 || ints[pos - 1] == 1))
                        || (ints[pos] == 1 && (pos == grid[0].length - 1 || ints[pos + 1] == -1))) {
                    pos = -1;
                    break;
                }
                pos += ints[pos];
            }
            balls[col] = pos;
        }
        return balls;
    }
}

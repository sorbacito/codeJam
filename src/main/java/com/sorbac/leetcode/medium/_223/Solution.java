package com.sorbac.leetcode.medium._223;

public class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        return computeRect(ax1, ay1, ax2, ay2) + computeRect(bx1, by1, bx2, by2)
                - intersection(ax1, ax2, bx1, bx2) * intersection(ay1, ay2, by1, by2);
    }

    private int computeRect(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) * Math.abs(y2 - y1);
    }

    private int intersection(int a1, int a2, int b1, int b2) {
        int min = Math.min(a1, b1);
        int max = Math.max(a2, b2);
        int area = max - min;
        return Math.max((a2 - a1) + (b2 - b1) - area, 0);
    }
}

package com.sorbac.leetcode.easy._374;

public class Solution {
    public int guessNumber(int n) {
        int start = 1;
        int end = n;
        while(start <= end) {
            int cur = start + (end - start)/2;
            int ret_guess = guess(cur);
            if (ret_guess == 0) {
                return cur;
            } else if (ret_guess > 0) {
                start = cur + 1;
            } else {
                end = cur - 1;
            }
        }
        return -1;
    }

    /**
     * Mock implementation of guess function
     * Forward declaration of guess API.
     * @param  num   your guess
     * @return 	     -1 if num is higher than the picked number
     *			      1 if num is lower than the picked number
     *               otherwise return 0
     * int guess(int num);
     */
    private int guess(int num) {
        return 0;
    }
}

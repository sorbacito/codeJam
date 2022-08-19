package com.sorbac.leetcode.easy._566;

/**
 * <a href="https://leetcode.com/problems/reshape-the-matrix/">https://leetcode.com/problems/reshape-the-matrix/</a>
 */
public class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if (mat.length * mat[0].length != r * c) {
            return mat;
        }
        int[][] retMax = new int[r][c];
        int idx = 0;
        for (int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                retMax[idx/c][idx%c] = mat[i][j];
                idx++;
            }
        }
        return retMax;
    }
}

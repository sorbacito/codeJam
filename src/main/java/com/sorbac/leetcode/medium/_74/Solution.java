package com.sorbac.leetcode.medium._74;

/**
 * <a href="https://leetcode.com/problems/search-a-2d-matrix/submissions/">https://leetcode.com/problems/search-a-2d-matrix/submissions/</a>
 */
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix[0][0] > target ||
                matrix[matrix.length - 1][matrix[0].length - 1] < target) {
            return false;
        }
        int rowSize = matrix[0].length;
        int minId = 0;
        int maxId = matrix.length * matrix[0].length;
        while (minId <= maxId) {
            int mid = (minId + maxId)/2;
            int midValue = matrix[mid / rowSize][mid % rowSize];
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                minId = mid + 1;
            } else {
                maxId = mid - 1;
            }
        }
        return false;
    }
}

package com.sorbac.leetcode.medium._74;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3, true},
                {new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13, false}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[][] matrix, int target, boolean expectedResult) {
        assertThat(new Solution().searchMatrix(matrix, target), is(expectedResult));
    }
}
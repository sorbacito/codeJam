package com.sorbac.leetcode.easy._566;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.*;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[][]{{1, 2}, {3, 4}}, 1, 4, new int[][]{{1,2,3,4}}},
                {new int[][]{{1, 2}, {3, 4}}, 2, 4, new int[][]{{1, 2}, {3, 4}}}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[][] mat, int r, int c, int[][] expectedResult) {
        assertThat(new Solution().matrixReshape(mat, r, c), is(expectedResult));
    }

}
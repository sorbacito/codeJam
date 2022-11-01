package com.sorbac.leetcode.medium._1706;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[][] {{1,1,1,-1,-1},{1,1,1,-1,-1},{-1,-1,-1,1,1},{1,1,1,1,-1},{-1,-1,-1,-1,-1}}, new int[]{1,-1,-1,-1,-1}},
                {new int[][] {{-1}}, new int[]{-1}},
                {new int[][] {{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1},{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1}}, new int[]{0,1,2,3,4,-1}}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[][] grid, int[] expectedBalls) {
        assertThat(new Solution().findBall(grid), is(expectedBalls));
    }

}

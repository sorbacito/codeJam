package com.sorbac.leetcode.medium._223;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {-3, 0, 3, 4, 0, -1, 9, 2, 45},
                {-2, -2, 2, 2, -2, -2, 2, 2, 16}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2, int expectedResult) {
        assertThat(new Solution().computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2), is(expectedResult));
    }

}

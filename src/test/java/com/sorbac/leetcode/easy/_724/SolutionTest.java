package com.sorbac.leetcode.easy._724;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1,7,3,6,5,6}, 3},
                {new int[]{1,2,3}, -1},
                {new int[]{2,1,-1}, 0}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int expectedResult) {
        assertThat(new Solution().pivotIndex(nums), is(expectedResult));
    }

}

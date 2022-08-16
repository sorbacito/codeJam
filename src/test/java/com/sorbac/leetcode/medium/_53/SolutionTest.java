package com.sorbac.leetcode.medium._53;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{-2,1,-3,4,-1,2,1,-5,4}, 6},
                {new int[]{1}, 1},
                {new int[]{5,4,-1,7,8}, 23}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int expectedResult) {
        assertThat(new Solution().maxSubArray(nums), is(expectedResult));
    }
}
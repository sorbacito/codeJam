package com.sorbac.leetcode.medium._189;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1,2,3,4,5,6,7}, 3, new int[]{5,6,7,1,2,3,4}},
                {new int[]{-1,-100,3,99}, 2, new int[]{3,99,-1,-100}},
                {new int[]{1,2}, 0, new int[]{1,2}}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int k, int[] expectedResult) {
        new Solution().rotate(nums, k);
        assertThat(nums, is(expectedResult));
    }
}
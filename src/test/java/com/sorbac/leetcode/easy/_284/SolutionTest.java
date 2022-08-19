package com.sorbac.leetcode.easy._284;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{0,1,0,3,12}, new int[]{1,3,12,0,0}},
                {new int[]{0}, new int[]{0}}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int[] expectedResult) {
        new Solution().moveZeroes(nums);
        assertThat(nums, is(expectedResult));
    }
}
package com.sorbac.leetcode.easy._1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{2,7,11,15}, 9, new int[]{0, 1}},
                {new int[]{3,2,4}, 6, new int[]{1, 2}},
                {new int[]{3,3}, 6, new int[]{0, 1}}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int target, int[] expectedResult) {
        assertThat(new Solution().twoSum(nums, target), is(expectedResult));
    }

}
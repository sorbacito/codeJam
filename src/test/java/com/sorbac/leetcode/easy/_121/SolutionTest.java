package com.sorbac.leetcode.easy._121;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{7,1,5,3,6,4}, 5},
                {new int[]{7,6,4,3,1}, 0}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int expectedResult) {
        assertThat(new Solution().maxProfit(nums), is(expectedResult));
    }

}

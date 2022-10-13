package com.sorbac.leetcode.easy._1480;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1,2,3,4}, new int[]{1,3,6,10}},
                {new int[]{1,1,1,1,1}, new int[]{1,2,3,4,5}},
                {new int[]{3,1,2,10,1}, new int[]{3,4,6,16,17}}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int[] expectedResult) {
        assertThat(new Solution().runningSum(nums), is(expectedResult));
    }

}

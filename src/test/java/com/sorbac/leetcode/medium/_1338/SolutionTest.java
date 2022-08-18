package com.sorbac.leetcode.medium._1338;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.*;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[] {3,3,3,3,5,5,5,2,2,7}, 2},
                {new int[] {7,7,7,7,7,7}, 1}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int expectedResult) {
        assertThat(new Solution().minSetSize(nums), is(expectedResult));
    }

}

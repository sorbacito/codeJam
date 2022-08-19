package com.sorbac.leetcode.medium._659;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[] {1,2,3,3,4,5}, true},
                {new int[] {1,2,3,3,4,4,5,5}, true}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, boolean expectedResult) {
        assertThat(new Solution().isPossible(nums), is(expectedResult));
    }
}

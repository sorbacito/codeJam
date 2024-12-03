package com.sorbac.leetcode.easy._804;

import com.sorbac.leetcode.easy._977.Solution;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{-4, -1, 0, 3, 10}, new int[]{0, 1, 9, 16, 100}},
                {new int[]{-7, -3, 2, 3, 11}, new int[]{4, 9, 9, 49, 121}}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int[] expectedResult) {
        assertThat(new Solution().sortedSquares(nums), is(expectedResult));
    }
}

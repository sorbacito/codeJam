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
                {new String[] {"gin","zen","gig","msg"}, 2},
                {new String[] {"a"}, 1}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int[] expectedResult) {
        assertThat(new Solution().sortedSquares(nums), is(expectedResult));
    }
}

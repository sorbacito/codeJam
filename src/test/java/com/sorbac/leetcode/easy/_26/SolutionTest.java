package com.sorbac.leetcode.easy._26;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1,1,2}, new int[]{1,2}},
                {new int[]{0,0,1,1,1,2,2,3,3,4}, new int[]{0,1,2,3,4}}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums, int[] expectedResult) {
        int uniqueNums = new Solution().removeDuplicates(nums);
        for (int i = 0; i < uniqueNums; i++) {
            assertThat(nums[i], is(expectedResult[i]));
        }
    }
}

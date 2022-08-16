package com.sorbac.leetcode.easy._88;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.*;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3, new int[]{1,2,2,3,5,6}},
                {new int[]{1}, 1, new int[0], 0, new int[]{1}},
                {new int[]{0}, 0, new int[]{1}, 1, new int[]{1}}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums1, int m, int[] nums2, int n, int[] expectedResult) {
        new Solution().merge(nums1, m, nums2, n);
        assertThat(nums1, is(expectedResult));
    }
}
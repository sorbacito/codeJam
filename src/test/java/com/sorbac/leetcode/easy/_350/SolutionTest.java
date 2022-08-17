package com.sorbac.leetcode.easy._350;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.*;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1,2,2,1}, new int[]{2,2}, new int[]{2,2}},
                {new int[]{4,9,5}, new int[]{9,4,9,8,4}, new int[]{4,9}},
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[] nums1, int[] nums2, int[] expectedResult) {
        assertThat(new Solution().intersect(nums1, nums2), is(expectedResult));
    }

}

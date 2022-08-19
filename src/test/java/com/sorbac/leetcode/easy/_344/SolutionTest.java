package com.sorbac.leetcode.easy._344;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new char[]{'h','e','l','l','o'}, new char[]{'o','l','l','e','h'}},
                {new char[]{'H','a','n','n','a','h'}, new char[]{'h','a','n','n','a','H'}}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(char[] nums, char[] expectedResult) {
        new Solution().reverseString(nums);
        assertThat(nums, is(expectedResult));
    }
}
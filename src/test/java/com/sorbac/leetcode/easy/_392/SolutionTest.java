package com.sorbac.leetcode.easy._392;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {"abc", "ahbgdc", true},
                {"axc", "ahbgdc", false}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(String s, String t, boolean expectedResult) {
        assertThat(new Solution().isSubsequence(s, t), is(expectedResult));
    }
}
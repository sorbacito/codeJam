package com.sorbac.leetcode.easy._1544;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {"leEeetcode", "leetcode"},
                {"abBAcC", ""}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(String s, String expectedResult) {
        assertThat(new Solution().makeGood(s), is(expectedResult));
    }
}
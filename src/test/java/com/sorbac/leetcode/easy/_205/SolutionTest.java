package com.sorbac.leetcode.easy._205;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {"egg", "add", true},
                {"foo", "bar", false},
                {"paper", "title", true},
                {"badc", "baba", false}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(String a, String b, boolean expectedResult) {
        assertThat(new Solution().isIsomorphic(a, b), is(expectedResult));
    }
}
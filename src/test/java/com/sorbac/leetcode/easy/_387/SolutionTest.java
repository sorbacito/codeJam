package com.sorbac.leetcode.easy._387;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.*;

public class SolutionTest {

    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {"leetcode", 0},
                {"loveleetcode", 2},
                {"aabb", -1}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(String s, int expectedResult) {
        assertThat(new Solution().firstUniqChar(s), is(expectedResult));
    }
}
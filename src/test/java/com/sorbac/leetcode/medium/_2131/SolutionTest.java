package com.sorbac.leetcode.medium._2131;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new String[] {"lc","cl","gg"}, 6},
                {new String[] {"ab","ty","yt","lc","cl","ab"}, 8},
                {new String[] {"cc","ll","xx"}, 2}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(String[] words, int expectedResult) {
        assertThat(new Solution().longestPalindrome(words), is(expectedResult));
    }
}
package com.sorbac.leetcode.easy._804;

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
    public void testSolution(String[] words, int expectedResult) {
        assertThat(new Solution().uniqueMorseRepresentations(words), is(expectedResult));
    }

}

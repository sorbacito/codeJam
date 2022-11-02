package com.sorbac.leetcode.medium._433;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {"AACCGGTT", "AACCGGTA", new String[] {"AACCGGTA"}, 1},
                {"AACCGGTT", "AAACGGTA", new String[] {"AACCGGTA","AACCGCTA","AAACGGTA"}, 2},
                {"AAAAACCC", "AACCCCCC", new String[] {"AAAACCCC","AAACCCCC","AACCCCCC"}, 3}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(String start, String end, String[] bank, int expectedResult) {
        assertThat(new Solution().minMutation(start, end, bank), is(expectedResult));
    }
}
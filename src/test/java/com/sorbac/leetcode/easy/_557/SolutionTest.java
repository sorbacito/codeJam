package com.sorbac.leetcode.easy._557;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {"Let's take LeetCode contest", "s'teL ekat edoCteeL tsetnoc"},
                {"God Ding", "doG gniD"}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(String s, String expectedResult) {
        assertThat(new Solution().reverseWords(s), is(expectedResult));
    }

}
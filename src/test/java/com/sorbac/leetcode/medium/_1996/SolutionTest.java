package com.sorbac.leetcode.medium._1996;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.*;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {new int[][] {{5,5},{6,3},{3,6}}, 0},
                {new int[][] {{2,2},{3,3}}, 1},
                {new int[][] {{1,5},{10,4},{4,3}}, 1},
                {new int[][] {{1,1},{2,1},{2,2},{1,2}}, 1}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int[][] props, int expectedResult) {
        assertThat(new Solution().numberOfWeakCharacters(props), is(expectedResult));
    }
}
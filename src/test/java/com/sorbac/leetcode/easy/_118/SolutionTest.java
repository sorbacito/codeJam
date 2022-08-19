package com.sorbac.leetcode.easy._118;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {5, List.of(List.of(1),List.of(1,1),List.of(1,2,1),List.of(1,3,3,1),List.of(1,4,6,4,1))},
                {1, List.of(List.of(1))}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int row, List<List<Integer>> expectedResult) {
        assertThat(new Solution().generate(row), is(expectedResult));
    }
}
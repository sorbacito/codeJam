package com.sorbac.leetcode.hard._871;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][] {
                {1, 1, new int[][]{}, 0},
                {100, 1, new int[][]{{10, 100}}, -1},
                {100, 10, new int[][]{{10, 60}, {20, 30}, {30,30}, {60,40}}, 2},
                {1000, 83, new int[][]{{25,27},{36,187},{140,186},{378,6},{492,202},{517,89},{579,234},{673,86},{808,53},{954,49}}, -1},
                {100, 50, new int[][]{{25,25},{50,50}}, 1}
        };
    }

    @Test(dataProvider = "dp")
    public void testSolution(int target, int startFuel, int[][] stations, int expectedResult) {
        assertThat(new Solution().minRefuelStops(target, startFuel, stations), is(expectedResult));
    }
}
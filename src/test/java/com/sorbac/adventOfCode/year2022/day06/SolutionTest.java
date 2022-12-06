package com.sorbac.adventOfCode.year2022.day06;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {

    @DataProvider(name = "dpMarker")
    public Object[][] dataProviderMarker() {
        return new Object[][] {
                {"bvwbjplbgvbhsrlpgdmjqwftvncz", 5},
                {"nppdvjthqldpwncqszvftbrmjlhg", 6},
                {"nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10},
                {"zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11}
        };
    }

    @DataProvider(name = "dpMessage")
    public Object[][] dataProviderMessage() {
        return new Object[][] {
                {"mjqjpqmgbljsphdztnvjfqwrcgsmlb", 19},
                {"bvwbjplbgvbhsrlpgdmjqwftvncz", 23},
                {"nppdvjthqldpwncqszvftbrmjlhg", 23},
                {"nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 29},
                {"zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 26}
        };
    }

    @Test(dataProvider = "dpMarker")
    public void testSolutionMarker(String s, int expectedResult) {
        assertThat(new Solution().positionOfMarker(s), is(expectedResult));
    }
    @Test(dataProvider = "dpMessage")
    public void testSolutionMessage(String s, int expectedResult) {
        assertThat(new Solution().positionOfMessage(s), is(expectedResult));
    }
}

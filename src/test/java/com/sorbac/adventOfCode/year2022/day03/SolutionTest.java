package com.sorbac.adventOfCode.year2022.day03;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dpFirst")
    public Object[][] dataProviderMarker() {
        return new Object[][] {
                {"vJrwpWtwJgWrhcsFMMfFFhFp", 16},
                {"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", 38},
                {"PmmdzqPrVvPwwTWBwg", 42}
        };
    }

    @DataProvider(name = "dpSecond")
    public Object[][] dataProviderMessage() {
        return new Object[][] {
                {new String[]{"vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg"}, 18},
                {new String[]{"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw"}, 52},
        };
    }

    @Test(dataProvider = "dpFirst")
    public void testSolutionFirst(String s, int expectedResult) {
        assertThat(new Solution(s).getPriorityOfArrangedItem(), is(expectedResult));
    }
    @Test(dataProvider = "dpSecond")
    public void testSolutionSecond(String[] s, int expectedResult) {
        assertThat(new Solution(List.of(s)).getPriorityOfSharedItem(), is(expectedResult));
    }

}

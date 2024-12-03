package com.sorbac.adventOfCode.year2022.day15;

import org.testng.annotations.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @Test
    public void testSensor() {
        Solution.CaveSensor caveSensor = new Solution.CaveSensor(8, 7, new Solution.CaveBeacon(2, 10));
        assertThat(caveSensor.getCoverage(-2).size(), is(1));
    }

    //    @DataProvider(name = "dpMessage")
//    public Object[][] dataProviderMessage() {
//        return new Object[][] {
//                {"mjqjpqmgbljsphdztnvjfqwrcgsmlb", 19},
//                {"bvwbjplbgvbhsrlpgdmjqwftvncz", 23},
//                {"nppdvjthqldpwncqszvftbrmjlhg", 23},
//                {"nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 29},
//                {"zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 26}
//        };
//    }
//
    @Test
    public void testName() {
        Solution.CaveSensor caveSensor = new Solution.CaveSensor(8, 7, new Solution.CaveBeacon(2, 10));
        assertThat(caveSensor.getCoverageRange(-3), is(Optional.empty()));
        assertThat(caveSensor.getCoverageRange(-2), is(Optional.of(new Solution.Range(8, 8))));
        assertThat(caveSensor.getCoverageRange(7), is(Optional.of(new Solution.Range(-1, 17))));
        assertThat(caveSensor.getCoverageRange(8), is(Optional.of(new Solution.Range(0, 16))));
    }
}

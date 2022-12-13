package com.sorbac.adventOfCode.year2022.day13;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.*;

public class SolutionTest {
    @DataProvider(name = "dpFirst")
    public Object[][] dpFirst() {
        return new Object[][] {
                {new String[]{"[1,1,3,1,1]", "[1,1,5,1,1]"}, true}
        };
    }

    @Test(dataProvider = "dpFirst")
    public void testSolutionMarker(String[] s, boolean expectedResult) {
        assertThat(new Solution(s[0], s[1]).isSmaller(), is(expectedResult));
    }
}
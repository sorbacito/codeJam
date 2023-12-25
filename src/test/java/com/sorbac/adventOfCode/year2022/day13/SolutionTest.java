package com.sorbac.adventOfCode.year2022.day13;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @DataProvider(name = "dpFirst")
    public Object[][] dpFirst() {
        return new Object[][] {
                {new String[]{"[1,1,3,1,1]", "[1,1,5,1,1]"}, true},
                {new String[]{"[[1],[2,3,4]]", "[[1],4]"}, true},
                {new String[]{"[9]", "[[8,7,6]]"}, false},
                {new String[]{"[[4,4],4,4]", "[[4,4],4,4,4]"}, true},
                {new String[]{"[7,7,7,7]", "[7,7,7]"}, false},
                {new String[]{"[]", "[3]"}, true},
                {new String[]{"[[[]]]", "[[]]"}, false},
                {new String[]{"[1,[2,[3,[4,[5,6,7]]]],8,9]", "[1,[2,[3,[4,[5,6,0]]]],8,9]"}, false},
                {new String[]{"[22]", "[3]"}, false},
                {new String[]{"[[10],[],[[[6,5,2]],[6,1,4,2,2],2,1],[],[2,2,[[10,8,5],5,[5]]]]", "[[6],[],[10,3,[4],9,[[10,0,9,5]]],[[[3,10],[10,2,8,5],0,7],1,[[],4,3],2,6]]"}, false}
        };
    }

    @Test(dataProvider = "dpFirst")
    public void testSolutionMarker(String[] input, boolean expectedResult) {
//        assertThat(new Solution(input[0], input[1])
//                .isInRightOrder(), is(expectedResult));
    }
}

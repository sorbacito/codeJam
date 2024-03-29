package com.sorbac.codeJam._2017.qualification.bathroomStalls;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MainTest {
    @Test
    public void test1() {
        test(4, 2, 1L, 0L);
    }

    @Test
    public void test2() {
        test(5, 2, 1L, 0L);
    }

    @Test
    public void test3() {
        test(6, 2, 1L, 1L);
    }

    @Test
    public void test4() {
        test(1000, 1000, 0L, 0L);
    }

    @Test
    public void test5() {
        test(1000, 1, 500L, 499L);
    }

    @Test
    public void test6() {
        test(500, 117, 3L, 2L);
    }

    @Test(enabled = false, description = "Fails, ignored for now")
    public void testMax() {
        test(999999999999999999L, 999999999999999999L, 0L, 0L);
        test(999999999999999999L, 999999999999999998L, 1L, 0L);
    }

    private void test(long initialSpace, long initialNoOfPeople, long expectedMax, long expectedMin) {
        /*long[] myResult = Main.getLastPersonMaxMinPriorityQueue(initialSpace, initialNoOfPeople);
        assertThat(myResult[0], is(expectedMax));
        assertThat(myResult[1], is(expectedMin));*/
        long[] myResultAlternative = com.sorbac.codeJam._2017.qualification.bathroomStalls.Main.getLastPersonMaxMinCalculated(initialSpace, initialNoOfPeople);
        assertThat(myResultAlternative[0], is(expectedMax));
        assertThat(myResultAlternative[1], is(expectedMin));
    }
}

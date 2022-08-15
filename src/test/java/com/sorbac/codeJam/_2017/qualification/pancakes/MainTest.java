package com.sorbac.codeJam._2017.qualification.pancakes;

import com.sorbac.codeJam._2017.qualification.pancakes.Main;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MainTest {

    public static final long IMPOSSIBLE = -1L;

    @Test
    public void test1() throws Exception {
        test(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3, 3L);
    }

    @Test
    public void test2() throws Exception {
        test(new int[]{1, 1, 1, 1, 1}, 4, 0L);
        test(new int[]{1, 1, 1, 1, 0}, 1, 1L);
    }

    @Test
    public void test3() throws Exception {
        test(new int[]{0, 1, 0, 1, 0}, 4, IMPOSSIBLE);
        test(new int[]{1, 1, 1, 1, 0}, 2, IMPOSSIBLE);
    }

    private void test(int[] aPancakes, int aFlipperSize, long aExpectedFlips) {
        assertThat(new Main(aPancakes, aFlipperSize).countFlips(), is(aExpectedFlips));
    }
}
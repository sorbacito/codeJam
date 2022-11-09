package com.sorbac.leetcode.medium._901;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StockSpannerTest {
    @Test
    public void testExample1() {
        StockSpanner ss = new StockSpanner();
        assertThat(ss.next(100), is(1));
        assertThat(ss.next(80), is(1));
        assertThat(ss.next(60), is(1));
        assertThat(ss.next(70), is(2));
        assertThat(ss.next(60), is(1));
        assertThat(ss.next(75), is(4));
        assertThat(ss.next(85), is(6));
    }
}

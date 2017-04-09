package com.sorbac.codeJam.year2017.qualification.tidyNumbers;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MainTest {
    @Test
    public void test1() {
        assertThat(Main.generateBiggestTidyNymber(132L), is(129L));
        assertThat(Main.generateBiggestTidyNymber(134189L), is(133999L));
    }

    @Test
    public void test2() {
        assertThat(Main.generateBiggestTidyNymber(1000L), is(999L));
        assertThat(Main.generateBiggestTidyNymber(10L), is(9L));
    }

    @Test
    public void testFailing() throws Exception {
        assertThat(Main.generateBiggestTidyNymber(554L), is(499L));
        assertThat(Main.generateBiggestTidyNymber(991L), is(899L));
    }

    @Test
    public void testTidyNumbers() {
        assert Main.generateBiggestTidyNymber(7L) == 7;
        assertThat(Main.generateBiggestTidyNymber(1234L), is(1234L));
    }

    @Test
    public void testMax() throws Exception {
        assertThat(Main.generateBiggestTidyNymber(999999999999999999L), is(999999999999999999L));
    }

    @Test
    public void testDecomposeNumber() throws Exception {
        Integer[] decomposedNumber = Main.decomposeNumber(1290L);
        int i = 0;
        assertThat(decomposedNumber[i++], is(1));
        assertThat(decomposedNumber[i++], is(2));
        assertThat(decomposedNumber[i++], is(9));
        assertThat(decomposedNumber[i], is(0));
    }

    @Test
    public void testGetFirstDigitBrakingTidiness() throws Exception {
        //assert Main.getFirsDigitIndexBrakingTidiness(132L) == 1;
    }
}
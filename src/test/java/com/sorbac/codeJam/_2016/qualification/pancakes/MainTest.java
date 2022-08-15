package com.sorbac.codeJam._2016.qualification.pancakes;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MainTest {

    @Test
    public void testExamples() throws Exception {
        assertEquals(new Main(new int[]{0}, 0).countFlips(), 1);
        assertEquals(new Main(new int[]{0, 1}, 1).countFlips(), 1);
        assertEquals(new Main(new int[]{1, 0}, 1).countFlips(), 2);
        assertEquals(new Main(new int[]{1, 1, 1},  2).countFlips(), 0);
        assertEquals(new Main(new int[]{0, 0, 0},  2).countFlips(), 1);
        assertEquals(new Main(new int[]{0, 0, 1, 0}, 3).countFlips(), 3);
        assertEquals(new Main(new int[]{0, 0, 1, 1, 1, 1, 0, 1, 0}, 8).countFlips(), 5);
    }
}
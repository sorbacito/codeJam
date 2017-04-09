package com.sorbac.codeJam.year2016.qualification.countingSheep;

import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

public class MainTest {

    @Test
    public void testExamples() throws Exception {
        assertEquals(test(0), "INSOMNIA");
        assertEquals(test(1), "10");
        assertEquals(test(2), "90");
        assertEquals(test(11), "110");
        assertEquals(test(1692), "5076");
    }

    private String test(long i) {
        return new Main(i).countSheeps();
    }

    @Test
    public void testSmall() throws Exception {
        Random myRandom = new Random();
        for(int i = 0; i < 100; i++) {
            new Main(myRandom.nextInt(201)).countSheeps();
        }
    }

    @Test
    public void testLarge() throws Exception {
        Random myRandom = new Random();
        for(int i = 0; i < 100; i++) {
            new Main(myRandom.nextInt(1000001)).countSheeps();
        }
    }
}
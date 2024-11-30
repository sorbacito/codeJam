package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.stream.IntStream;

public class Day20 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day20.class.getSimpleName().replaceFirst("Day", ""));

    protected Day20() {
        super(DAY);
    }

    protected Day20(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
//        new Day20("10").printParts(1);
//        new Day20("30").printParts(2);
//        new Day20("40").printPart1(3);
//        new Day20("60").printPart1(4);
        new Day20("70").printPart1(4);
        new Day20("130").printPart1(8);
        new Day20("34000000").printParts(786240);
    }

    @Override
    protected Object part1() {
        int value = Integer.parseInt(day());
        return findFirstHouse(value, 10, Integer.MAX_VALUE);
    }

    @Override
    protected Object part2() {
        int value = Integer.parseInt(day());
        return findFirstHouse(value, 11, 50);

    }

    private static int findFirstHouse(int value, int presentValue, int maxPresents) {
        long[] houses = new long[value / presentValue + 1];
        for (int i = 1; i <= houses.length; i++) {
            for (int j = i; j <= maxPresents && j < houses.length; j += i) {
                houses[j - 1] += i * presentValue;
            }
        }
        return IntStream.range(0, houses.length).filter(i -> houses[i] >= value).findFirst().orElseThrow();
    }
}

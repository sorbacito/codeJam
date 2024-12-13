package com.sorbac.adventOfCode.year2024.day;

import com.sorbac.adventOfCode.year2024.Day2024;

public class DayX extends Day2024 {
    private static final int DAY = Integer.parseInt(DayX.class.getSimpleName().replaceFirst("Day", ""));

    protected DayX() {
        super(DAY);
    }

    protected DayX(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new DayX().printParts();
        new DayX(testInput1()).printPart1(1);
        new DayX(testInput2()).printPart2(2);
    }

    private static String testInput1() {
        return null;
    }

    private static String testInput2() {
        return testInput1();
    }

    @Override
    protected Object part1() {
        return null;
    }

    @Override
    protected Object part2() {
        return null;
    }
}

package com.sorbac.adventOfCode.year2024.day;

import com.sorbac.adventOfCode.year2024.Day2024;

public class Day1 extends Day2024 {
    private static final int DAY = Integer.parseInt(Day1.class.getSimpleName().replaceFirst("Day", ""));

    protected Day1() {
        super(DAY);
    }

    protected Day1(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day1().printParts();
        new Day1("test input 1").printPart1("expected result 1");
        new Day1("test input 2").printPart1("expected result 2");
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

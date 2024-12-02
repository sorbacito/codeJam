package com.sorbac.adventOfCode.year2017.day;

import com.sorbac.adventOfCode.year2017.Day2017;

public class DayX extends Day2017 {
    private static final int DAY = Integer.parseInt(DayX.class.getSimpleName().replaceFirst("Day", ""));

    protected DayX() {
        super(DAY);
    }

    protected DayX(String input) {
        super(DAY, input);
    }

    protected DayX(String... lines) {
        super(DAY, lines);
    }

    public static void main(String[] args) {
        new DayX().printParts();
        new DayX("test input 1").printPart1("expected result 1");
        new DayX("test input 2").printPart2("expected result 2");
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

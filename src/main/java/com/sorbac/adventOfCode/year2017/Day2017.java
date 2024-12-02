package com.sorbac.adventOfCode.year2017;

import com.sorbac.adventOfCode.common.Day;

public abstract class Day2017 extends Day {
    protected static final int YEAR = Integer.parseInt(Day2017.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2017(int day) {
        super(YEAR, day);
    }

    public Day2017(int day, String input) {
        super(YEAR, day, input);
    }

    public Day2017(int day, String... lines) {
        super(YEAR, day, lines);
    }
}

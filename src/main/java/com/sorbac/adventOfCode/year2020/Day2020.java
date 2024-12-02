package com.sorbac.adventOfCode.year2020;

import com.sorbac.adventOfCode.common.Day;

public abstract class Day2020 extends Day {
    protected static final int YEAR = Integer.parseInt(Day2020.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2020(int day) {
        super(YEAR, day);
    }

    public Day2020(int day, String input) {
        super(YEAR, day, input);
    }
}

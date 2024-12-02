package com.sorbac.adventOfCode.year2018;

import com.sorbac.adventOfCode.common.Day;

public abstract class Day2018 extends Day {
    protected static final int YEAR = Integer.parseInt(Day2018.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2018(int day) {
        super(YEAR, day);
    }

    public Day2018(int day, String input) {
        super(YEAR, day, input);
    }
}

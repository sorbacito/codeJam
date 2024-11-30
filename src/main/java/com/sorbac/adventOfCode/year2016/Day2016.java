package com.sorbac.adventOfCode.year2016;

import com.sorbac.adventOfCode.common.Day;

public abstract class Day2016 extends Day {
    private static final int YEAR = Integer.parseInt(Day2016.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2016(int day) {
        super(YEAR, day);
    }

    public Day2016(int day, String input) {
        super(YEAR, day, input);
    }
}

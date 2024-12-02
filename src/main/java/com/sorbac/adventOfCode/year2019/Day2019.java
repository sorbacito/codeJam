package com.sorbac.adventOfCode.year2019;

import com.sorbac.adventOfCode.common.Day;

public abstract class Day2019 extends Day {
    protected static final int YEAR = Integer.parseInt(Day2019.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2019(int day) {
        super(YEAR, day);
    }

    public Day2019(int day, String input) {
        super(YEAR, day, input);
    }
}

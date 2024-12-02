package com.sorbac.adventOfCode.year2021;

import com.sorbac.adventOfCode.common.Day;

public abstract class Day2021 extends Day {
    protected static final int YEAR = Integer.parseInt(Day2021.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2021(int day) {
        super(YEAR, day);
    }

    public Day2021(int day, String input) {
        super(YEAR, day, input);
    }
}

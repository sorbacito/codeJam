package com.sorbac.adventOfCode.year2024;

import com.sorbac.adventOfCode.common.Day;

public abstract class Day2024 extends Day {
    protected static final int YEAR = Integer.parseInt(Day2024.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2024(int day) {
        super(YEAR, day);
    }

    public Day2024(int day, String input) {
        super(YEAR, day, input);
    }
}

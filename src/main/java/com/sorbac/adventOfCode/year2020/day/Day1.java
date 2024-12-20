package com.sorbac.adventOfCode.year2020.day;

import com.sorbac.adventOfCode.year2020.Day2020;

import java.util.Set;
import java.util.stream.Collectors;

public class Day1 extends Day2020 {
    private static final int DAY = Integer.parseInt(Day1.class.getSimpleName().replaceFirst("Day", ""));

    protected Day1() {
        super(DAY);
    }

    protected Day1(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day1().printParts();
        new Day1("""
                1721
                979
                366
                299
                675
                1456""").printParts(514579, 241861950);
//        new Day1("test input 2").printPart1("expected result 2");
    }

    @Override
    protected Object part1() {
        Set<Integer> values = dayStreamLines().map(Integer::parseInt).collect(Collectors.toSet());
        for (Integer value : values) {
            if (values.contains(2020 - value)) {
                return value * (2020 - value);
            }
        }
        return -1;
    }

    @Override
    protected Object part2() {
        Set<Integer> values = dayStreamLines().map(Integer::parseInt).collect(Collectors.toSet());
        for (Integer value : values) {
            for (Integer value2 : values) {
                if (values.contains(2020 - value - value2)) {
                    return value * value2 * (2020 - value - value2);
                }
            }
        }
        return -1;
    }
}

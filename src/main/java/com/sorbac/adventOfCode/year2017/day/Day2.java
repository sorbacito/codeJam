package com.sorbac.adventOfCode.year2017.day;

import com.sorbac.adventOfCode.year2017.Day2017;

import java.util.LongSummaryStatistics;

public class Day2 extends Day2017 {
    private static final int DAY = Integer.parseInt(Day2.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2() {
        super(DAY);
    }

    protected Day2(String input) {
        super(DAY, input);
    }

    protected Day2(String... lines) {
        super(DAY, lines);
    }

    public static void main(String[] args) {
        new Day2().printParts(36174, 244);
        new Day2("5 1 9 5",
                "7 5 3 ",
                "2 4 6 8").printPart1(18);
        new Day2("5 9 2 8\n" +
                "9 4 7 3\n" +
                "3 8 6 5").printPart2(9);
    }

    @Override
    protected Object part1() {
        return dayStreamLinesByString(ANY_BLANK_CHARACTERS_REGEX).mapToInt(
                listOfStrings -> {
                    LongSummaryStatistics stats = listOfStrings.stream().mapToLong(Long::parseLong).summaryStatistics();
                    return (int) (stats.getMax() - stats.getMin());
                }
        ).sum();
    }

    @Override
    protected Object part2() {
        return dayStreamLinesByString(ANY_BLANK_CHARACTERS_REGEX).mapToInt(
                strings -> {
                    for (int i = 0; i < strings.size(); i++) {
                        for (int j = 0; j < strings.size(); j++) {
                            if (i != j) {
                                long first = Long.parseLong(strings.get(i));
                                long second = Long.parseLong(strings.get(j));
                                if (first % second == 0) {
                                    return (int) (first / second);
                                }
                            }
                        }
                    }
                    return 0;
                }
        ).sum();
    }
}

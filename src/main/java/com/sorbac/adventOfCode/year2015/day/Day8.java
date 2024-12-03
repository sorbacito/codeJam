package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;

public class Day8 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day8.class.getSimpleName().replaceFirst("Day", ""));

    protected Day8() {
        super(DAY);
    }

    protected Day8(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        String first = "\"\"";
        new Day8(first).printParts(2, 4);
        String second = "\"abc\"";
        new Day8(second).printParts(2, 4);
        String third = "\"aaa\\\"aaa\"";
        new Day8(third).printParts(3, 6);
        String fourth = "\"\\x27\"";
        new Day8(fourth).printParts(5, 5);
        new Day8(String.join(DEFAULT_DELIMITER, first, second, third, fourth)).printParts(12, 19);
        new Day8().printParts(1371, 2117);
    }

    @Override
    protected Object part1() {
        int afterEscapingCount = dayStreamLines().map(s -> s.substring(1, s.length() - 1))
                .map(s -> s.replaceAll("\\\\\\\\", "\\\\"))
                .map(s -> s.replaceAll("\\\\\"", "\""))
                .map(s -> s.replaceAll("\\\\x[a-f0-9]{2}", "a"))
                .mapToInt(String::length)
                .sum();
        return dayStreamLines().mapToInt(String::length).sum() - afterEscapingCount;
    }

    @Override
    protected Object part2() {
        int afterAddingEscapeCharsSum = dayStreamLines().map(s -> s.replaceAll("\\\\", "\\\\\\\\"))
                .map(s -> s.replaceAll("\"", "\\\\\""))
                .map(s -> "\"" + s + "\"")
                .mapToInt(String::length).sum();
        return afterAddingEscapeCharsSum - dayStreamLines().mapToInt(String::length).sum();
    }
}

package com.sorbac.adventOfCode.year2024.day;

import com.sorbac.adventOfCode.year2024.Day2024;

import java.util.ArrayList;
import java.util.List;

public class Day2 extends Day2024 {
    private static final int DAY = Integer.parseInt(Day2.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2() {
        super(DAY);
    }

    protected Day2(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day2().printParts();
        new Day2("7 6 4 2 1\n" +
                "1 2 7 8 9\n" +
                "9 7 6 2 1\n" +
                "1 3 2 4 5\n" +
                "8 6 4 4 1\n" +
                "1 3 6 7 9").printPart1(2L);
        new Day2("7 6 4 2 1\n" +
                "1 2 7 8 9\n" +
                "9 7 6 2 1\n" +
                "1 3 2 4 5\n" +
                "8 6 4 4 1\n" +
                "1 3 6 7 9").printPart2(4L);
    }

    @Override
    protected Object part1() {
        return dayStreamLongs(" ").map(line -> {
            return isSafe(line);
        }).filter(isTrue -> isTrue).count();
    }

    @Override
    protected Object part2() {
        return dayStreamLongs(" ").map(line -> {
            List<List<Long>> allPossible = new ArrayList<>();
            allPossible.add(line);
            for (int i = 0; i < line.size(); i++) {
                List<Long> newLine = new ArrayList<>(line);
                newLine.remove(i);
                allPossible.add(newLine);
            }
            return allPossible.stream().anyMatch(Day2::isSafe);
        }).filter(isTrue -> isTrue).count();
    }

    private static Boolean isSafe(List<Long> line) {
        boolean increasing = line.get(1) - line.get(0) > 0;
        for (int i = 1; i < line.size(); i++) {
            long diff = line.get(i) - line.get(i - 1);
            if (diff > 0 != increasing
                    || Math.abs(diff) > 3 || Math.abs(diff) == 0) {
                return false;
            }
        }
        return true;
    }
}

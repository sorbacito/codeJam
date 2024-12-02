package com.sorbac.adventOfCode.year2021.day;

import com.sorbac.adventOfCode.year2021.Day2021;

import java.util.ArrayList;
import java.util.List;

public class Day1 extends Day2021 {
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
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263""").printParts(7, 5);
    }

    @Override
    protected Object part1() {
        List<Integer> values = dayStreamLines().map(Integer::parseInt).toList();
        return increasesCounts(values);
    }

    @Override
    protected Object part2() {
        List<Integer> sums = new ArrayList<>();
        List<Integer> values = dayStreamLines().map(Integer::parseInt).toList();
        for (int i = 2; i < values.size(); i++) {
            sums.add(values.get(i) + values.get(i - 1) + values.get(i - 2));
        }
        return increasesCounts(sums);
    }

    private static int increasesCounts(List<Integer> values) {
        int count = 0;
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) > values.get(i - 1)) {
                count++;
            }
        }
        return count;
    }
}

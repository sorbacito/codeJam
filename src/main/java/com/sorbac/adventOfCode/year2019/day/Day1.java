package com.sorbac.adventOfCode.year2019.day;


import com.sorbac.adventOfCode.year2019.Day2019;

import java.util.function.ToIntFunction;

public class Day1 extends Day2019 {
    private static final int DAY = Integer.parseInt(Day1.class.getSimpleName().replaceFirst("Day", ""));

    protected Day1() {
        super(DAY);
    }

    protected Day1(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day1().printParts();
        new Day1("12").printParts(2, 2);
        new Day1("1969").printParts(654, 966);
        new Day1("100756").printParts(33583, 50346);
//        new Day1("test input 2").printPart2("expected result 2");
    }

    @Override
    protected Object part1() {
        return dayStreamLines().map(Integer::parseInt).mapToInt(calculateFuel()).sum();
    }

    @Override
    protected Object part2() {
        return dayStreamLines().map(Integer::parseInt).mapToInt(weight -> {
            int sum = 0;
            do {
                weight = calculateFuel().applyAsInt(weight);
                sum += Math.max(weight, 0);
            } while (weight > 0);
            return sum;
        }).sum();
    }

    private static ToIntFunction<Integer> calculateFuel() {
        return i -> i / 3 - 2;
    }
}

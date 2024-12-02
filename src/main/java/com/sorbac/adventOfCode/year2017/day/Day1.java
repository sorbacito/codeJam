package com.sorbac.adventOfCode.year2017.day;

import com.sorbac.adventOfCode.year2017.Day2017;

import java.util.function.Function;

public class Day1 extends Day2017 {
    private static final int DAY = Integer.parseInt(Day1.class.getSimpleName().replaceFirst("Day", ""));

    protected Day1() {
        super(DAY);
    }

    protected Day1(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day1().printParts();
        new Day1("1122").printPart1(3);
        new Day1("1111").printPart1(4);
        new Day1("1234").printPart1(0);
        new Day1("91212129").printPart1(9);
        new Day1("1212").printPart2(6);
        new Day1("1221").printPart2(0);
        new Day1("123425").printPart2(4);
        new Day1("123123").printPart2(12);
        new Day1("12131415").printPart2(4);
//        new Day2("test input 2").printPart1("expected result 2");
    }

    @Override
    protected Object part1() {
        String input = day().replace("\n", "");
        return sumOfMatchinItems(i -> input.charAt((i + 1) % input.length()));
    }

    private int sumOfMatchinItems(Function<Integer, Character> matchingValue) {
        String input = day().replace("\n", "");
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == matchingValue.apply(i)) {
                sum += Character.getNumericValue(currentChar);
            }
        }
        return sum;
    }

    @Override
    protected Object part2() {
        String input = day().replace("\n", "");
        int offset = input.length() / 2;
        return sumOfMatchinItems(i -> input.charAt((i + offset) % input.length()));
    }
}

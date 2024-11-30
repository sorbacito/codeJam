package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;

public class Day1 extends Day2015 {
    protected Day1() {
        super(1);
    }

    protected Day1(String input) {
        super(1, input);
    }

    public static void main(String[] args) {
        new Day1("(())").printPart1(0L);
        new Day1("()()").printPart1(0L);
        new Day1("))(((((").printPart1(3L);
        new Day1(")())())").printPart1(-3L);

        new Day1(")").printPart2(1L);
        new Day1("()())").printPart2(5L);
        new Day1().printParts(280L, 1797);
    }

    @Override
    protected Object part1() {
        String input = day();
        long up = input.chars().filter(c -> c == '(').count();
        long down = input.chars().filter(c -> c == ')').count();
        return up - down;
    }

    @Override
    protected Object part2() {
        int floor = 0;
        String input = day();
        for (int i = 0; i < input.length(); i++) {
            floor += input.charAt(i) == '(' ? 1 : -1;
            if (floor == -1) {
                return (long) i + 1;
            }
        }
        return -1;
    }
}

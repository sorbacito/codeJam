package com.sorbac.adventOfCode.year2018.day;

import com.sorbac.adventOfCode.year2018.Day2018;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Day1 extends Day2018 {
    private static final int DAY = Integer.parseInt(Day1.class.getSimpleName().replaceFirst("Day", ""));

    protected Day1() {
        super(DAY);
    }

    protected Day1(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day1().printParts();
        new Day1(String.join("\n", "+1", "+1", "+1")).printPart1(3);
        new Day1(String.join("\n", "+1", "+1", "-2")).printPart1(0);
        new Day1(String.join("\n", "-1", "-2", "-3")).printPart1(-6);
//        new Day2("test input 2").printPart1("expected result 2");
    }

    @Override
    protected Object part1() {
        return getIntStream().sum();
    }

    private IntStream getIntStream() {
        return readLines().stream()
                .mapToInt(line -> {
                    int value = Integer.parseInt(line.substring(1));
                    if (line.charAt(0) == '+') {
                        return value;
                    } else {
                        return value * -1;
                    }
                });
    }

    @Override
    protected Object part2() {
        List<Integer> values = getIntStream().boxed().toList();
        Set<Integer> visited = new HashSet<>();
        int sum = 0;
        int i = 0;
        while (true) {
            sum += values.get(i);
            if (visited.contains(sum)) {
                return sum;
            }
            visited.add(sum);
            i = (i + 1) % values.size();
        }
    }
}

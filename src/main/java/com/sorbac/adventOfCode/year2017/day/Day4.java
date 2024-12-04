package com.sorbac.adventOfCode.year2017.day;

import com.sorbac.adventOfCode.year2017.Day2017;

import java.util.Arrays;
import java.util.List;

public class Day4 extends Day2017 {
    private static final int DAY = Integer.parseInt(Day4.class.getSimpleName().replaceFirst("Day", ""));

    protected Day4() {
        super(DAY);
    }

    protected Day4(String input) {
        super(DAY, input);
    }

    protected Day4(String... lines) {
        super(DAY, lines);
    }

    public static void main(String[] args) {
        new Day4().printParts(451, 223);
        new Day4("aa bb cc dd ee").printPart1(1);
        new Day4("aa bb cc dd aa").printPart1(0);
        new Day4("aa bb cc dd aaa").printPart1(1);
        new Day4("abcde fghij").printPart2(1);
        new Day4("abcde xyz ecdab").printPart2(0);
        new Day4("a ab abc abd abf abj").printPart2(1);
        new Day4("iiii oiii ooii oooi oooo").printPart2(1);
        new Day4("oiii ioii iioi iiio").printPart2(0);
    }

    @Override
    protected Object part1() {
        return dayStreamLines().mapToInt(line -> {
            List<String> list = Arrays.stream(line.split(" ")).toList();
            return atLeastOneSameWord(list);
        }).sum();
    }

    private static int atLeastOneSameWord(List<String> list) {
        for (String word : list) {
            if (list.stream().filter(word::equals).count() > 1) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    protected Object part2() {
        return dayStreamLines().mapToInt(line -> {
            List<String> list = Arrays.stream(line.split(" ")).map(word -> {
                char[] chars = word.toCharArray();
                Arrays.sort(chars);
                return new String(chars);
            }).toList();
            return atLeastOneSameWord(list);
        }).sum();
    }
}

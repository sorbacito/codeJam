package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Day5 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day5.class.getSimpleName().replaceFirst("Day", ""));
    public static final long NICE = 1L;
    public static final long NAUGHTY = 0L;

    protected Day5() {
        super(DAY);
    }

    protected Day5(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        System.out.println("Part 1:");
        new Day5("ugknbfddgicrmopn").printParts(NICE);
        new Day5("aaa").printParts(NICE);
        new Day5("jchzalrnumimnmhp").printParts(NAUGHTY);
        new Day5("haegwjzuvuyypxyu").printParts(NAUGHTY);
        new Day5("dvszwmarrgswjxmb").printParts(NAUGHTY);

        System.out.println("\nPart 2:");
        new Day5("qjhvhtzxzqqjkmpb").printPart2(NICE);
        new Day5("xxyxx").printPart2(NICE);
        new Day5("uurcxstgmygtbstg").printPart2(NAUGHTY);
        new Day5("ieodomkazucvgmuy").printPart2(NAUGHTY);

        new Day5().printParts(258L, 53L);
    }

    @Override
    protected Object part1() {
        return dayStream().filter(this::isNice1).count();
    }

    private boolean isNice1(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        List<Character> uniqueVowels = s.chars().mapToObj(c -> (char) c).filter(vowels::contains).toList();
        boolean isDoubleLetter = IntStream.range(0, s.length() - 1)
                .filter(i -> s.charAt(i) == s.charAt(i + 1))
                .mapToObj(i -> true).findAny().orElse(false);
        Set<String> notPermitted = Set.of("ab", "cd", "pq", "xy");
        boolean hasNotPermittedStrings = IntStream.range(0, s.length() - 1)
                .mapToObj(i -> s.substring(i, i + 2))
                .filter(notPermitted::contains)
                .map(i -> true).findAny().orElse(false);
        return uniqueVowels.size() >= 3 && isDoubleLetter && !hasNotPermittedStrings;
    }

    @Override
    protected Object part2() {
        return dayStream().filter(this::isNice2).count();
    }

    private boolean isNice2(String s) {
        boolean firstCondition = IntStream.range(0, s.length() - 2)
                .filter(i -> s.substring(i + 2).contains(s.substring(i, i + 2)))
                .findFirst().orElse(-1) > -1;
        boolean secondCondition = IntStream.range(0, s.length() - 2)
                .filter(i -> s.charAt(i) == s.charAt(i + 2))
                .findFirst().orElse(-1) > -1;
        return firstCondition && secondCondition;
    }
}

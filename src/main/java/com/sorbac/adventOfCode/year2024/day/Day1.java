package com.sorbac.adventOfCode.year2024.day;

import com.sorbac.adventOfCode.common.Pair;
import com.sorbac.adventOfCode.year2024.Day2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day1 extends Day2024 {
    private static final int DAY = Integer.parseInt(Day1.class.getSimpleName().replaceFirst("Day", ""));

    protected Day1() {
        super(DAY);
    }

    protected Day1(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day1().printParts(2742123, 21328497L);
        new Day1(String.join("\n",
                "3   4", "4   3", "2   5", "1   3", "3   9", "3   3")).printPart1(11);
        new Day1(String.join("\n",
                "3   4", "4   3", "2   5", "1   3", "3   9", "3   3")).printPart2(31L);
    }

    @Override
    protected Object part1() {
        Pair<List<Integer>, List<Integer>> lists = getLists();

        List<Integer> list1Sorted = lists.getFirst().stream().sorted().toList();
        List<Integer> list2Sorted = lists.getSecond().stream().sorted().toList();

        int sum = 0;
        for (int i = 0; i < list1Sorted.size(); i++) {
            sum += Math.abs(list1Sorted.get(i) - list2Sorted.get(i));
        }
        return sum;
    }

    @Override
    protected Object part2() {
        Pair<List<Integer>, List<Integer>> lists = getLists();

        List<Integer> list1 = lists.getFirst();
        List<Integer> list2 = lists.getSecond();

        Map<Integer, Long> map2Frequency = list2.stream()
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()));

        long sum = 0;
        for (Integer integer : list1) {
            sum += integer * map2Frequency.getOrDefault(integer, 0L);
        }
        return sum;
    }

    private Pair<List<Integer>, List<Integer>> getLists() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        dayStreamLines().map(line -> line.split(ANY_BLANK_CHARACTERS_REGEX))
                .map(parts -> new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])})
                .forEach(parts -> {
                    list1.add(parts[0]);
                    list2.add(parts[1]);
                });
        return new Pair<>(list1, list2);
    }
}

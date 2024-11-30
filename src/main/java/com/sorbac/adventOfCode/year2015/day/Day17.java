package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.ArrayList;
import java.util.List;

public class Day17 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day17.class.getSimpleName().replaceFirst("Day", ""));

    protected Day17() {
        super(DAY);
    }

    protected Day17(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day17(String.join(DEFAULT_DELIMITER, "20", "15", "10", "5", "5")).setArgument(25).printParts(4, 3);
        new Day17().setArgument(150).printParts(1304, 18); // 1: too low 249
    }

    @Override
    protected Object part1() {
        List<Integer> values = dayStreamLongs().map(List::getFirst).mapToInt(Long::intValue).boxed().toList();
        List<List<Integer>> combs = allCombinationsOfSum(values, (int) getArgument());
        return combs.size();
    }

    @Override
    protected Object part2() {
        List<Integer> values = dayStreamLongs().map(List::getFirst).mapToInt(Long::intValue).boxed().toList();
        List<List<Integer>> combs = allCombinationsOfSum(values, (int) getArgument());
        Integer minContainers = combs.stream().map(List::size).min(Integer::compareTo).orElse(-1);
        return (int) combs.stream().filter(comb -> comb.size() == minContainers).count();
    }

    private List<List<Integer>> allCombinationsOfSum(List<Integer> values, int sum) {
        List<List<Integer>> combs = new ArrayList<>();
        combs.add(new ArrayList<>());
        for (Integer value : values) {
            List<List<Integer>> newCombs = new ArrayList<>();
            for (List<Integer> comb : combs) {
                int curSum = comb.stream().mapToInt(Integer::intValue).sum();
                if (curSum + value <= sum) {
                    List<Integer> newComb = new ArrayList<>(comb);
                    newComb.add(value);
                    newCombs.add(newComb);
                }
            }
            combs.addAll(newCombs);
        }
        return combs.stream().filter(comb -> comb.stream().mapToInt(Integer::intValue).sum() == sum).toList();
    }
}

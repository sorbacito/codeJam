package com.sorbac.adventOfCode.year2016.day;


import com.sorbac.adventOfCode.common.Tuple;
import com.sorbac.adventOfCode.year2016.Day2016;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Day3 extends Day2016 {
    private static final int DAY = Integer.parseInt(Day3.class.getSimpleName().replaceFirst("Day", ""));

    protected Day3() {
        super(DAY);
    }

    protected Day3(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day3(" 5 10 25").printPart1(0);
        new Day3().printParts(993, 1849);
    }

    @Override
    protected Object part1() {
        return (int) dayStream().map(line -> line.split("\\s+"))
                .map(array -> new Tuple<>(Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3])))
                .filter(isTriangle())
                .count();
    }

    @Override
    protected Object part2() {
        List<Tuple<Integer, Integer, Integer>> list = dayStream().map(line -> line.split("\\s+"))
                .map(array -> new Tuple<>(Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3])))
                .toList();
        List<Tuple<Integer, Integer, Integer>> colTriangles = new ArrayList<>();
        for (int i = 0; i < list.size() / 3; i++) {
            Tuple<Integer, Integer, Integer> tupleA = new Tuple<>(list.get(i * 3).getA(), list.get(i * 3 + 1).getA(), list.get(i * 3 + 2).getA());
            colTriangles.add(tupleA);
            Tuple<Integer, Integer, Integer> tupleB = new Tuple<>(list.get(i * 3).getB(), list.get(i * 3 + 1).getB(), list.get(i * 3 + 2).getB());
            colTriangles.add(tupleB);
            Tuple<Integer, Integer, Integer> tupleC = new Tuple<>(list.get(i * 3).getC(), list.get(i * 3 + 1).getC(), list.get(i * 3 + 2).getC());
            colTriangles.add(tupleC);
        }
        return colTriangles.stream().filter(isTriangle()).count();

    }

    private static Predicate<Tuple<Integer, Integer, Integer>> isTriangle() {
        return tuple -> tuple.getA() + tuple.getB() > tuple.getC() &&
                tuple.getA() + tuple.getC() > tuple.getB() &&
                tuple.getB() + tuple.getC() > tuple.getA();
    }
}

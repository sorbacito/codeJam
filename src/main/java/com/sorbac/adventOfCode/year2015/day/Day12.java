package com.sorbac.adventOfCode.year2015.day;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sorbac.adventOfCode.year2015.Day2015;

import java.lang.reflect.Type;
import java.util.*;

public class Day12 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day12.class.getSimpleName().replaceFirst("Day", ""));
    public static final boolean IS_PART_1 = false;
    public static final boolean IS_PART_2 = true;

    protected Day12() {
        super(DAY);
    }

    protected Day12(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
//        new Day12("{\"a\":2,\"b\":4}").printPart1(6);
//        new Day12("{\"a\":{\"b\":4},\"c\":-1}").printPart1(3);
//        new Day12("{\"a\":[-1,1]}").printPart1(0);
//        new Day12("{}").printPart1(0);
////        new Day12("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}").printParts(15, 0);
//        new Day12("{\"e\":[1,\"red\",5]}").printParts(6, 6);

        System.out.println("\nSolution:");
        new Day12().printParts(111754, 65402); //38885 too low
    }

    @Override
    protected Object part1() {
        return part(IS_PART_1);
    }

    @Override
    protected Object part2() {
        return part(IS_PART_2);
    }

    private int part(boolean isPart2) {
        double sum = 0;
        Deque<Object> nodes = loadJson();
        while (!nodes.isEmpty()) {
            Object node = nodes.poll();
            if (node instanceof Map) {
                Collection<Object> values = ((Map<String, Object>) node).values();
                if (!isPart2 || values.stream().noneMatch("red"::equals)) nodes.addAll(values);
            } else if (node instanceof List) {
                nodes.addAll((List<Object>) node);
            } else if (node instanceof Double) {
                sum += (Double) node;
            }
        }
        return (int) sum;
    }

    private Deque<Object> loadJson() {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> map = gson.fromJson(day(), mapType);
        Deque<Object> nodes = new LinkedList<>();
        nodes.add(map);
        return nodes;
    }
}

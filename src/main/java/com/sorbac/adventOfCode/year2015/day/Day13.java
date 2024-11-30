package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.common.Pair;
import com.sorbac.adventOfCode.common.Tuple;
import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sorbac.adventOfCode.util.AoCUtils.allPermutations;

public class Day13 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day13.class.getSimpleName().replaceFirst("Day", ""));

    protected Day13() {
        super(DAY);
    }

    protected Day13(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day13().setExample(1).printParts();
        new Day13().printParts();
    }

    @Override
    protected Object part1() {
        Map<String, List<Pair<String, Integer>>> map = loadHappiness();
        return maxHappinessPermutation(map);
    }

    @Override
    protected Object part2() {
        Map<String, List<Pair<String, Integer>>> map = loadHappiness();
        String meName = "me";
        map.put(meName, new ArrayList<>());
        map.keySet().forEach(k -> {
            map.get(meName).add(new Pair<>(k, 0));
            map.get(k).add(new Pair<>(meName, 0));
        });
        return maxHappinessPermutation(map);
    }

    private static int maxHappinessPermutation(Map<String, List<Pair<String, Integer>>> map) {
        return allPermutations(map.keySet()).stream().mapToInt(l -> {
            int sum = 0;
            for (int i = 0; i < l.size(); i++) {
                String name = l.get(i);
                String nextName = l.get((i + 1) % l.size());
                sum += map.get(name).stream().filter(p -> p.left().equals(nextName)).mapToInt(Pair::right).sum();
                sum += map.get(nextName).stream().filter(p -> p.left().equals(name)).mapToInt(Pair::right).sum();
            }
            return sum;
        }).max().orElse(-1);
    }

    private Map<String, List<Pair<String, Integer>>> loadHappiness() {
        return dayStream().map(Day13::parseLine)
                .collect(Collectors.toMap(Tuple::a, t -> new ArrayList<>() {{
                    add(new Pair<>(t.b(), t.c()));
                }}, (a, b) -> {
                    a.addAll(b);
                    return a;
                }));
    }

    private static Tuple<String, String, Integer> parseLine(String line) {
        String[] split = line.split(" happiness units by sitting next to ");
        String nextToName = split[1].replace(".", "");
        String[] splitName = split[0].split(" would gain ");
        if (splitName.length == 2) {
            return new Tuple<>(splitName[0], nextToName, Integer.parseInt(splitName[1]));
        } else {
            splitName = split[0].split(" would lose ");
            return new Tuple<>(splitName[0], nextToName, -Integer.parseInt(splitName[1]));
        }
    }
}

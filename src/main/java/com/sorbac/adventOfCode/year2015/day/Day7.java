package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Day7 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day7.class.getSimpleName().replaceFirst("Day", ""));

    protected Day7() {
        super(DAY);
    }

    private List<String> queries;

    protected Day7(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        ((Day7) new Day7().setExample(1)).setQueries(List.of("d", "e", "f", "g", "h", "i", "x", "y")).printParts(List.of(72, 507, 492, 114, 65412, 65079, 123, 456));
        new Day7().setQueries(List.of("a")).printParts(List.of(46065), List.of(14134));
    }

    private Day7 setQueries(List<String> queries) {
        this.queries = queries;
        return this;
    }

    @Override
    protected Object part1() {
        Map<String, Supplier<Integer>> circuit = getCircuit();
        return queries.stream().mapToInt(s -> circuit.get(s).get()).boxed().toList();
    }

    private Map<String, Supplier<Integer>> getCircuit() {
        Map<String, Supplier<Integer>> circuit = new HashMap<>() {
            @Override
            public Supplier<Integer> get(Object key) {
                Supplier<Integer> integerSupplier = super.get(key);
                Integer i = integerSupplier.get();
                put((String) key, () -> i);
                return () -> i;
            }
        };
        dayStream().forEach(s -> {
            String[] split = s.split(" -> ");
            circuit.put(split[1], createSupplier(split[0], circuit));
        });
        return circuit;
    }


    private Supplier<Integer> createSupplier(String s, Map<String, Supplier<Integer>> circuit) {
        if (s.contains(" AND ")) {
            String[] split = s.split(" AND ");
            return () -> {
//                System.out.println(s);
                return getInteger(circuit, split[0]).get() & getInteger(circuit, split[1]).get();
            };
        } else if (s.contains(" OR ")) {
            String[] split = s.split(" OR ");
            return () -> getInteger(circuit, split[0]).get() | getInteger(circuit, split[1]).get();
        } else if (s.contains(" LSHIFT ")) {
            String[] split = s.split(" LSHIFT ");
            return () -> getInteger(circuit, split[0]).get() << Integer.parseInt(split[1]);
        } else if (s.contains(" RSHIFT ")) {
            String[] split = s.split(" RSHIFT ");
            return () -> getInteger(circuit, split[0]).get() >> Integer.parseInt(split[1]);
        } else if (s.contains("NOT ")) {
            String[] split = s.split("NOT ");
            return () -> ~getInteger(circuit, split[1]).get() & 0xFFFF;
        } else {
            return () -> getInteger(circuit, s).get();
        }
    }

    private static Supplier<Integer> getInteger(Map<String, Supplier<Integer>> circuit, String s) {
        if (s.matches("\\d+")) {
            return () -> Integer.parseInt(s);
        }
        return circuit.get(s);
    }

    @Override
    protected Object part2() {
        Integer bValue = ((List<Integer>) part1()).getFirst();
        Map<String, Supplier<Integer>> circuit = getCircuit();
        circuit.put("b", () -> bValue);
        return queries.stream().mapToInt(s -> circuit.get(s).get()).boxed().toList();
    }
}

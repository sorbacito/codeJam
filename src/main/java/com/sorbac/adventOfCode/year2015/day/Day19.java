package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.common.Pair;
import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.*;
import java.util.stream.Collectors;

public class Day19 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day19.class.getSimpleName().replaceFirst("Day", ""));

    protected Day19() {
        super(DAY);
    }

    protected Day19(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
//        new Day19().setExample(1).printPart1(4);
        new Day19().printParts(509);
    }

    @Override
    protected Object part1() {
        Map<String, Set<String>> replacementMap = loadReplacements();
        String molecule = loadMolecule();
        Set<String> possibleMolecules = getPossibleMolecules(replacementMap, molecule);
        return possibleMolecules.size();
    }

    @Override
    protected Object part2() {
        Map<String, Set<String>> replacementMap = loadReplacements().entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(val -> new Pair<>(val, entry.getKey())))
                .collect(Collectors.toMap(Pair::left, p -> new HashSet<>() {{
                    add(p.right());
                }}, (a, b) -> {
                    a.addAll(b);
                    return a;
                }));
        String molecule = loadMolecule();
        Deque<Pair<Integer, String>> molecules = new LinkedList<>();
        molecules.add(new Pair<>(0, molecule));
        while (true) {
            Pair<Integer, String> mol = molecules.poll();
            Set<String> possibleMolecules = getPossibleMolecules(replacementMap, mol.right());
            if (possibleMolecules.contains("e")) {
                return mol.left() + 1;
            }
            possibleMolecules.stream().sorted(Comparator.comparingInt(String::length).reversed())
                    .forEach(m -> molecules.addFirst(new Pair<>(mol.left() + 1, m)));
        }
    }

    private String loadMolecule() {
        return dayStream().filter(line -> !line.isEmpty() && !line.contains("=>")).findFirst().orElseThrow();
    }

    private Map<String, Set<String>> loadReplacements() {
        return dayStream().filter(line -> line.contains("=>"))
                .map(line -> line.split(" => "))
                .collect(Collectors.toMap(line -> line[0], line -> new HashSet<>() {{
                    add(line[1]);
                }}, (a, b) -> {
                    a.addAll(b);
                    return a;
                }));
    }

    private static Set<String> getPossibleMolecules(Map<String, Set<String>> replacementMap, String molecule) {
        return replacementMap.keySet().stream().map(key -> {
            Set<String> newMolecules = new HashSet<>();
            Set<String> replacements = replacementMap.get(key);
            int index = molecule.indexOf(key);
            while (index >= 0) {
                int myIdx = index;
                replacements.forEach(replacement -> newMolecules.add(molecule.substring(0, myIdx) + replacement + molecule.substring(myIdx + key.length())));
                index = molecule.indexOf(key, index + 1);
            }
            return newMolecules;
        }).flatMap(Set::stream).collect(Collectors.toSet());
    }
}

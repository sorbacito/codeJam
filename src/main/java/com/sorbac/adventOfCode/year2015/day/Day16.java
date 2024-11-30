package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.Arrays;
import java.util.List;

public class Day16 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day16.class.getSimpleName().replaceFirst("Day", ""));

    protected Day16() {
        super(DAY);
    }

    protected Day16(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day16().printParts();
    }

    @Override
    protected Object part1() {
        List<List<String>> sues = dayStream().map(Day16::parseSue).toList();
        for (int i = 0; i < sues.size(); i++) {
            List<String> sue = sues.get(i);
            if (Arrays.stream(("children: 3\n" +
                    "cats: 7\n" +
                    "samoyeds: 2\n" +
                    "pomeranians: 3\n" +
                    "akitas: 0\n" +
                    "vizslas: 0\n" +
                    "goldfish: 5\n" +
                    "trees: 3\n" +
                    "cars: 2\n" +
                    "perfumes: 1").split("\n")).toList().containsAll(sue)) {
                return i + 1;
            }
        }
        return -1;
    }

    private static List<String> parseSue(String line) {
        return Arrays.stream(line.split(", ")).map(s -> s.split(" "))
                .map(sArr -> sArr[sArr.length - 2] + " " + sArr[sArr.length - 1]).toList();
    }

    @Override
    protected Object part2() {
        List<List<String>> sues = dayStream().map(Day16::parseSue).toList();
        for (int i = 0; i < sues.size(); i++) {
            List<String> sue = sues.get(i);
            List<String> basicSue = sue.stream().filter(s -> s.startsWith("children") || s.startsWith("samoyeds") || s.startsWith("akitas")
                    || s.startsWith("vizslas") || s.startsWith("cars")
                    || s.startsWith("perfumes")).toList();
            if (Arrays.stream(("children: 3\n" +
                    "samoyeds: 2\n" +
                    "akitas: 0\n" +
                    "vizslas: 0\n" +
                    "cars: 2\n" +
                    "perfumes: 1").split("\n")).toList().containsAll(basicSue) &&
                    sue.stream().filter(s -> s.startsWith("cats")).map(s -> Integer.parseInt(s.split(" ")[1])).findFirst().orElse(10) > 7 &&
                    sue.stream().filter(s -> s.startsWith("trees")).map(s -> Integer.parseInt(s.split(" ")[1])).findFirst().orElse(10) > 3 &&
                    sue.stream().filter(s -> s.startsWith("pomeranians")).map(s -> Integer.parseInt(s.split(" ")[1])).findFirst().orElse(0) < 3 &&
                    sue.stream().filter(s -> s.startsWith("goldfish")).map(s -> Integer.parseInt(s.split(" ")[1])).findFirst().orElse(0) < 5) {
                return i + 1;
            }
        }
        return -1;
    }
}

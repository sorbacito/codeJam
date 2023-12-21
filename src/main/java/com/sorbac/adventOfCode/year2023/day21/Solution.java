package com.sorbac.adventOfCode.year2023.day21;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day21/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day21/test11.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day21/input1.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day21/test2.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day21/test22.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day21/input2.txt"))));

        List<String> lines1 = new ArrayList<>();
        while (in1.hasNextLine()) {
            lines1.add(in1.nextLine());
        }
        long part1Answer = getPart1Answer(lines1);
//        System.out.println(part1Answer);

        List<String> lines2 = new ArrayList<>();
        while (in2.hasNextLine()) {
            lines2.add(in2.nextLine());
        }

        System.out.println(26501365 / 131 + " * 131 + " + 26501365 % 131);
        System.out.println(getPart2TryAnswer(lines2, 65));
        System.out.println(getPart2TryAnswer(lines2, 65 + 131));
        System.out.println(getPart2TryAnswer(lines2, 65 + 2*131));
        System.out.println(getPart2TryAnswer(lines2, 65 + 3*131));
        System.out.println(getPart2TryAnswer(lines2, 65 + 5*131));
    }

    private static long getPart1Answer(List<String> lines) {
        Pos start = findStart(lines);
        Set<Pos> current = new HashSet<>();
        current.add(start);
        for (int i = 0; i < 64; i++) {
            Set<Pos> next = new HashSet<>();
            for (Pos pos : current) {
                if(isValid(lines, pos.row() - 1, pos.col())) {
                    next.add(new Pos(pos.row() - 1, pos.col()));
                }
                if (isValid(lines, pos.row() + 1, pos.col())) {
                    next.add(new Pos(pos.row() + 1, pos.col()));
                }
                if (isValid(lines, pos.row(), pos.col() - 1)) {
                    next.add(new Pos(pos.row(), pos.col() - 1));
                }
                if (isValid(lines, pos.row(), pos.col() + 1)) {
                    next.add(new Pos(pos.row(), pos.col() + 1));
                }
            }
            current = next;
        }
        return current.size();
    }

    private static long getPart2TryAnswer(List<String> lines, int steps) {
        Pos start = findStart(lines);
        Set<Pos> current = new HashSet<>();
        current.add(start);
        for (int i = 0; i < steps; i++) {
            Set<Pos> next = new HashSet<>();
            for (Pos pos : current) {
                if(isValid2(lines, pos.row() - 1, pos.col())) {
                    next.add(new Pos(pos.row() - 1, pos.col()));
                }
                if (isValid2(lines, pos.row() + 1, pos.col())) {
                    next.add(new Pos(pos.row() + 1, pos.col()));
                }
                if (isValid2(lines, pos.row(), pos.col() - 1)) {
                    next.add(new Pos(pos.row(), pos.col() - 1));
                }
                if (isValid2(lines, pos.row(), pos.col() + 1)) {
                    next.add(new Pos(pos.row(), pos.col() + 1));
                }
            }
            current = next;
        }
        return current.size();
    }

    private static boolean isValid(List<String> lines, int row, int col) {
        return row >= 0 && row < lines.size() && col >= 0 && col < lines.get(row).length() && lines.get(row).charAt(col) != '#';
    }

    private static Pos findStart(List<String> lines) {
        return IntStream.range(0, lines.size())
                .mapToObj(row -> IntStream.range(0, lines.get(row).length())
                    .mapToObj(col -> {
                        if (lines.get(row).charAt(col) == 'S') {
                            return new Pos(row, col);
                        }
                        return null;
                    }).filter(Objects::nonNull).toList()
                )
                .filter(l -> !l.isEmpty())
                .findFirst()
                .get().get(0);
    }

    private static long getPart2Answer(List<String> lines) {
        Pos start = findStart(lines);
        Map<Pair<Pos, Integer>, Set<Pos>> cache = new HashMap<>();
        IntStream.range(1, 100).forEach(
                i -> {
                    System.out.println("Steps :" + i);
                    System.out.println(getDistinctTiles(lines, start, i, cache).size());
                }
        );
        return 0;
    }

    private static Set<Pos> getDistinctTiles(List<String> lines, Pos pos, int steps, Map<Pair<Pos, Integer>, Set<Pos>> cache) {
        Set<Pos> positions = new HashSet<>();
        Set<Pos> cacheRes = cache.get(new Pair<>(pos, steps));
        if (cacheRes != null) {
            return cacheRes;
        }
        if (steps == 1) {
            if(isValid2(lines, pos.row() - 1, pos.col())) {
                positions.add(new Pos(pos.row() - 1, pos.col()));
            }
            if (isValid2(lines, pos.row() + 1, pos.col())) {
                positions.add(new Pos(pos.row() + 1, pos.col()));
            }
            if (isValid2(lines, pos.row(), pos.col() - 1)) {
                positions.add(new Pos(pos.row(), pos.col() - 1));
            }
            if (isValid2(lines, pos.row(), pos.col() + 1)) {
                positions.add(new Pos(pos.row(), pos.col() + 1));
            }
            cache.put(new Pair<>(pos, steps), positions);
            return positions;
        }
        if(isValid2(lines, pos.row() - 1, pos.col())) {
            positions.addAll(getDistinctTiles(lines, new Pos(pos.row() - 1, pos.col()), steps - 1, cache));
        }
        if (isValid2(lines, pos.row() + 1, pos.col())) {
            positions.addAll(getDistinctTiles(lines, new Pos(pos.row() + 1, pos.col()), steps - 1, cache));
        }
        if (isValid2(lines, pos.row(), pos.col() - 1)) {
            positions.addAll(getDistinctTiles(lines, new Pos(pos.row(), pos.col() - 1), steps - 1, cache));
        }
        if (isValid2(lines, pos.row(), pos.col() + 1)) {
            positions.addAll(getDistinctTiles(lines, new Pos(pos.row(), pos.col() + 1), steps - 1, cache));
        }
        cache.put(new Pair<>(pos, steps), positions);
        return positions;
    }

    private static boolean isValid2(List<String> lines, int row, int col) {
        int maxRow = lines.size();
        int maxCol = lines.get(0).length();
        int modRow = ((row % maxRow) + maxRow) % maxRow;
        int modCol = ((col % maxCol) + maxCol) % maxCol;
        return lines.get(modRow).charAt(modCol) != '#';
    }

    private  record Pos(int row, int col) {}

    private record Pair<A, B>(A a, B b) {}

    private enum Dir {
        UP, DOWN, LEFT, RIGHT
    }
}

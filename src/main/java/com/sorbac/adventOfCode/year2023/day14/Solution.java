package com.sorbac.adventOfCode.year2023.day14;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day14/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day14/test11.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day14/input1.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day14/test2.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day14/test22.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day14/input2.txt"))));

//        List<String> lines1 = new ArrayList<>();
//        while (in1.hasNextLine()) {
//            lines1.add(in1.nextLine());
//        }
//        long part1Answer = getPart1Answer(lines1);
//        System.out.println(part1Answer);

        List<String> lines2 = new ArrayList<>();
        while (in2.hasNextLine()) {
            lines2.add(in2.nextLine());
        }
        System.out.println(getPart2Answer(lines2));
    }

    private static long getPart1Answer(List<String> lines) {
        long sum = 0L;
        for (int col = 0; col < lines.get(0).length(); col++) {
            int max = lines.size();
            for (int row = 0; row < lines.size(); row++) {
                char c = lines.get(row).charAt(col);
                if (c == 'O') {
                    sum += max--;
                } else if (c == '#') {
                    max = lines.size() - row - 1;
                }
            }
        }
        return sum;
    }

    private static long getPart2Answer(List<String> lines) {
        lines = tilt(lines);
        lines = turn(lines);
        lines = tilt(lines);
        lines = tilt(lines);
        lines = tilt(lines);
        return getPart1Answer(lines);
    }

    private static List<String> turn(List<String> lines) {
        return IntStream.range(0, lines.get(0).length())
                .mapToObj(col -> IntStream.range(0, lines.size())
                        .mapToObj(row -> lines.get(lines.size() - row - 1).charAt(col)).map(Objects::toString).collect(Collectors.joining())
                ).collect(Collectors.toList());
    }

    private static List<String> tilt(List<String> lines) {
        return IntStream.range(0, lines.get(0).length()).mapToObj(
                col -> {
                    StringBuilder nL = new StringBuilder();
                    int row = 0;
                    while (row < lines.size()) {
                        int hIdx = IntStream.range(row, lines.size()).filter(r -> lines.get(r).charAt(col) == '#').findFirst().orElse(-1);
                        int maxRow = hIdx == -1 ? lines.size() : hIdx;
                        long rocNum = IntStream.range(row, maxRow).filter(r -> lines.get(r).charAt(col) == 'O').count();
                        while (row <= maxRow) {
                            if (rocNum > 0) {
                                nL.append("O");
                                rocNum--;
                            } else {
                                if (row == maxRow) {
                                    nL.append("#");
                                } else {
                                    nL.append(".");
                                }
                            }
                            row++;
                        }
                    }
                    String s = nL.toString();
                    return s;
                }
        ).collect(Collectors.toList());
    }

    private static int getReflection(List<String> lines) {
        int length = lines.get(0).length();
        List<Integer> reflections = IntStream.range(1, length).boxed().toList();
        for (String line : lines) {
            reflections = findReflections(line, reflections);
        }

        return reflections.size() > 0 ?
                reflections.get(0)
                : 0;
    }

    private static int getReflectionWithSmudge(List<String> lines) {
        int length = lines.get(0).length();
        List<RefWS> reflections = IntStream.range(1, length).mapToObj(i -> new RefWS(i, 0)).toList();
        for (String line : lines) {
            reflections = findReflectionsWithSmudge(line, reflections);
        }

        return reflections.size() > 0?
                reflections.stream().anyMatch(r -> r.smudges == 1) ? reflections.stream().filter(r -> r.smudges == 1).findFirst().get().reflexion : 0
                : 0;
    }

    private static List<String> switchLines(List<String> lines) {
        List<String> newLines = new ArrayList<>();
        for (int i = 0; i < lines.get(0).length(); i++) {
            int idx = i;
            newLines.add(lines.stream().map(l -> l.charAt(idx)).map(Object::toString).collect(Collectors.joining()));
        }
        return newLines;
    }

    private static List<Integer> findReflections(String line, List<Integer> reflections) {
        List<Integer> ret = new ArrayList<>();
        for (int ref : reflections) {
            int diff = Math.min(ref, line.length() - ref);
            boolean isRef = true;
            for (int i = 0; i < diff; i++) {
                int left = ref - i - 1;
                int rigth = ref + i;
                if(line.charAt(left) != line.charAt(rigth)) {
                    isRef = false;
                    break;
                }
            }
            if (isRef) ret.add(ref);
        }
        return ret;
    }

    private static List<RefWS> findReflectionsWithSmudge(String line, List<RefWS> reflections) {
        List<RefWS> ret = new ArrayList<>();
        for (RefWS ref : reflections) {
            int diff = Math.min(ref.reflexion, line.length() - ref.reflexion);
            int smudges = ref.smudges;
            for (int i = 0; i < diff; i++) {
                int left = ref.reflexion - i - 1;
                int rigth = ref.reflexion + i;
                if(line.charAt(left) != line.charAt(rigth)) {
                    smudges++;
                    if (smudges > 1) break;
                }
            }
            if (smudges <= 1) ret.add(new RefWS(ref.reflexion, smudges));
        }
        return ret;
    }

    private record RefWS(int reflexion, int smudges) {}
}

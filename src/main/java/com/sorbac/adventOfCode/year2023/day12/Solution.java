package com.sorbac.adventOfCode.year2023.day12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day12/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day12/test11.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day12/input1.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day12/test2.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day12/input2.txt"))));

        List<String> lines1 = new ArrayList<>();
        while (in1.hasNextLine()) {
            lines1.add(in1.nextLine());
        }
//        long part1Answer = getPart1Answer(lines1);
//        System.out.println(part1Answer);

        List<String> lines2 = new ArrayList<>();
        while (in2.hasNextLine()) {
            lines2.add(in2.nextLine());
        }
        System.out.println(getPart2Answer(lines2));
    }

    private static long getPart1Answer(List<String> lines) {
        long ret = 0;

        for (String line : lines) {
            String[] lineSplit = line.split(" ");
            String springs = lineSplit[0];
            List<Integer> groups = Arrays.stream(lineSplit[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());
            ret += getArrangements(springs, groups);
//            System.out.println(" ");
//            getArrangements(springs, groups);
        }

        return ret;
    }

    private static long getPart2Answer(List<String> lines) {
        long ret = 0;

        for (String line : lines) {
            String[] lineSplit = line.split(" ");
            String springs = lineSplit[0] + "?" + lineSplit[0] + "?" + lineSplit[0] + "?" + lineSplit[0] + "?" + lineSplit[0];
            List<Integer> grps = Arrays.stream(lineSplit[1].split(",")).map(Integer::parseInt).toList();
            List<Integer> groups = new ArrayList<>(grps);
            groups.addAll(grps);
            groups.addAll(grps);
            groups.addAll(grps);
            groups.addAll(grps);
            ret += getArrangements(springs, groups);
            System.out.println("Current ret " + ret);
        }

        return ret;
    }

    private static int getArrangements(String springs, List<Integer> groups) {
        int ret = 0;
        List<String> finArr = new ArrayList<>();
        List<String> arrs = new ArrayList<>();
        arrs.add(springs);

        while (!arrs.isEmpty()) {
            List<String> newArrs = new ArrayList<>();
            for (String arr : arrs) {
                if (isPossibleOutcome(groups, arr)) {
                    if (arr.indexOf('?') != -1) {
                        newArrs.add(arr.replaceFirst("\\?", "."));
                        newArrs.add(arr.replaceFirst("\\?", "#"));
                    } else {
                        finArr.add(arr);
                    }
                } else {
                    int b = 0;
                }
            }
            arrs = newArrs;
        }

        Comparator<List<Integer>> listComparator = getListComparator();

        for (String arr : finArr) {
            if (listComparator.compare(countGroups(arr), groups) == 0) {
//                System.out.println(arr);
                ret++;
            }
        }

        return ret;
    }

    private static boolean isPossibleOutcome(List<Integer> groups, String arr) {
        List<String> grps = Arrays.stream(arr.split("\\.")).filter(s -> s.length() > 0).toList();
        if (!(grps.stream().filter(s -> !s.chars().allMatch(c -> c == '?')).map(String::length).count()
                <= groups.size())) {
            return false;
        }
        for (int i = 0; i < Math.min(grps.size(), groups.size()); i++) {
            String segment = grps.get(i);
            if (segment.contains("?")) {
                if (segment.indexOf("?") > groups.get(i)) {
                    return false;
                }
            } else if (segment.length() != groups.get(i)) {
                return false;
            }
        }
        return true;
    }

    private static int getArrangementsOrig(String springs, List<Integer> groups) {
        int ret = 0;
        List<String> finArr = new ArrayList<>();
        List<String> arrs = new ArrayList<>();
        arrs.add(springs);

        while (!arrs.isEmpty()) {
            List<String> newArrs = new ArrayList<>();
            for (String arr : arrs) {
                if (arr.indexOf('?') != -1) {

                    newArrs.add(arr.replaceFirst("\\?", "."));
                    newArrs.add(arr.replaceFirst("\\?", "#"));
                } else {
                    finArr.add(arr);
                }
            }
            arrs = newArrs;
        }

        Comparator<List<Integer>> listComparator = getListComparator();

        for (String arr : finArr) {
            if (listComparator.compare(countGroups(arr), groups) == 0) {
//                System.out.println(arr);
                ret++;
            }
        }

        return ret;
    }

    private static List<Integer> countGroups(String arr) {
        return Arrays.stream(arr.split("\\.")).map(String::length).filter(l -> l > 0).collect(Collectors.toList());
    }

    private static Comparator<List<Integer>> getListComparator() {
        return new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if (o1.size() == o2.size()) {
                    for (int i = 0; i < o1.size(); i++) {
                        if (o1.get(i) != o2.get(i)) {
                            return -1;
                        }
                    }
                    return 0;
                }
                return 1;
            }
        };
    }
    public record Tuple<A, B, C>(A a, B b, C c) {
        public static <A, B, C> Tuple<A, B, C> of(A a, B b, C c) {
            return new Tuple(a, b, c);
        }
    }

}

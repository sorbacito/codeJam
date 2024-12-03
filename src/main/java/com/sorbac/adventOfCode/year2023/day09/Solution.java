package com.sorbac.adventOfCode.year2023.day09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day09/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day09/test11.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day09/input1.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day09/test2.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day09/input2.txt"))));

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
        long part2Answer = getPart2Answer(lines2);
        System.out.println(part2Answer);
    }

    private static long getPart1Answer(List<String> lines) {
        long sum = 0;

        for (String line : lines) {
            String[] split = line.split(" ");
            List<Long> seq = Arrays.stream(split).sequential().mapToLong(Long::parseLong).boxed().toList();
            sum += extrSeq(seq);
        }

        return sum;
    }

    private static long extrSeq(List<Long> seq) {
        long sum = 0;
        while (!seq.stream().allMatch(l -> l == 0)) {
            System.out.println(seq.stream().map(String::valueOf).collect(Collectors.joining(", ")));
            sum += seq.get(seq.size() - 1);
            List<Long> newSeq = new ArrayList<>();
            for (int i = 1; i < seq.size(); i++) {
                newSeq.add(seq.get(i) - seq.get(i - 1));
            }
            seq = newSeq;
        }
        return sum;
    }

    private static long getPart2Answer(List<String> lines) {
        long sum = 0;

        for (String line : lines) {
            String[] split = line.split(" ");
            List<Long> seq = Arrays.stream(split).sequential().mapToLong(Long::parseLong).boxed().toList();
            sum += extrSeq2(seq);
        }

        return sum;
    }

    private static long extrSeq2(List<Long> seq) {
        long sum = 0;
        List<List<Long>> hist = new ArrayList<>();
        while ((long) seq.stream().collect(Collectors.groupingBy(l -> l)).keySet().size() > 1) {
            hist.add(seq);
//            System.out.println(seq.stream().map(String::valueOf).collect(Collectors.joining(", ")));
            sum += seq.get(0);
            List<Long> newSeq = new ArrayList<>();
            for (int i = 1; i < seq.size(); i++) {
                newSeq.add(seq.get(i) - seq.get(i - 1));
            }
            seq = newSeq;
        }
        long val = seq.get(0);

        for (int i = hist.size() - 1; i >= 0; i--) {
            List<Long> longs = hist.get(i);
            val = longs.get(0) - val;
            System.out.print(val + ", ");
            System.out.println(longs.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        }
        System.out.println("");

        return val;
    }


}

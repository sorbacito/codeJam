package com.sorbac.adventOfCode.year2023.day08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day08/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day08/test11.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day08/input1.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day08/test2.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day08/input2.txt"))));

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
        long part2Answer = getPart2Answer(lines2);
        System.out.println(part2Answer);

        System.out.println(getPart2AAnswer());
    }

    private static long getPart2AAnswer() {
        List<Integer> nums = List.of(12599, 17287, 17873, 13771, 15529, 21389);
        long lcm = 1;
        for (Integer i : nums) {
            lcm = lcm(lcm, i);
        }
        return lcm;
    }

    private static long getPart1Answer(List<String> lines) {
        String ins = lines.get(0);

        Map<String, List<String>> nodes = new HashMap<>();
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] split = line.split(" = \\(");
            String[] splitLR = split[1].split(", ");
            String[] splitR = splitLR[1].split("\\)");
            nodes.put(split[0], List.of(splitLR[0], splitR[0]));
        }

        long sum = 0;
        String cur = "AAA";
        while (true) {
            for (Character c : ins.toCharArray()) {
                if (c == 'L') {
                    cur = nodes.get(cur).get(0);
                } else if (c == 'R') {
                    cur = nodes.get(cur).get(1);
                }
                sum++;
                if ("ZZZ".equals(cur)) {
                    return sum;
                }
            }
        }
    }

    private static long getPart2Answer(List<String> lines) {
        String ins = lines.get(0);

        Map<String, List<String>> nodes = new HashMap<>();
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] split = line.split(" = \\(");
            String[] splitLR = split[1].split(", ");
            String[] splitR = splitLR[1].split("\\)");
            nodes.put(split[0], List.of(splitLR[0], splitR[0]));
        }

        long sum = 0;
        List<String> parNodes = nodes.keySet().stream().filter(s -> s.endsWith("A")).toList();
        Map<String, List<Position>> curCicle = new HashMap<>();
        char[] steps = ins.toCharArray();
        for (String node : parNodes) {
            String cur = node;
            sum = 0;
            curCicle.put(node, new ArrayList<>());
            boolean finished = false;
            while (!finished) {
                for (int i = 0; i < steps.length; i++) {
                    if (steps[i] == 'L') {
                        cur = nodes.get(cur).get(0);
                    } else if (steps[i] == 'R') {
                        cur = nodes.get(cur).get(1);
                    }
                    sum++;
                    if (cur.endsWith("Z")) {
                        System.out.println("Found " + cur + " for " + node + " in " + sum + " steps");

                        Position position = new Position(cur, i);
                        if (curCicle.get(node).contains(position)) {
                            finished = true;
                            System.out.println("Finished for " + node + " in " + sum + " steps\n");
                            break;
                        }
                        curCicle.get(node).add(position);
                    }
                }
            }
        }
        return 0;
    }

    private record Position(String node, long step) {
    }

    // Function to calculate the GCD of two numbers using Euclidean algorithm
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Function to calculate the LCM of two numbers
    public static long lcm(long a, long b) {
        long gcdResult = gcd(a, b);
        return (a * b) / gcdResult;
    }

}

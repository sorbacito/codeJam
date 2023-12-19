package com.sorbac.adventOfCode.year2023.day19;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day19/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day19/test11.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day19/input1.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day19/test2.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day19/test22.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day19/input2.txt"))));

        List<String> lines1 = new ArrayList<>();
        while (in1.hasNextLine()) {
            lines1.add(in1.nextLine());
        }
        long part1Answer = getPart1Answer(lines1);
        System.out.println(part1Answer);

//        List<String> lines2 = new ArrayList<>();
//        while (in2.hasNextLine()) {
//            lines2.add(in2.nextLine());
//        }
//        System.out.println(getPart2Answer(lines2));
    }

    private static long getPart1Answer(List<String> lines) {
        Map<String, List<Rule>> map = new HashMap<>();
        int iL = 0;
        while (!lines.get(iL).isEmpty()) {
            String s = lines.get(iL).split("\\}")[0];
            String name = s.split("\\{")[0];
            String[] rules = s.split("\\{")[1].split(",");
            List<Rule> rls = new ArrayList<>();
            for (String r : rules) {
                rls.add(Rule.of(r));
            }
            map.put(name, rls);
            iL++;
        }
        List<Shape> shapes = new ArrayList<>();
        while (++iL < lines.size()){
            String[] vals = lines.get(iL).split("\\}")[0].split("\\{")[1].split(",");
            shapes.add(new Shape(getVal(vals, 0), getVal(vals, 1), getVal(vals, 2), getVal(vals, 3)));
        }

        return shapes.stream().filter(shape -> {
            String flow = "in";
            while (true) {
                for (Rule fun : map.get(flow)) {
                    String res = fun.getFunction().apply(shape);
                    if (res == null) continue;
                    if (res.equals("A")) return true;
                    if (res.equals("R")) return false;
                    flow = res;
                    break;
                }
            }
        }).mapToLong(Shape::sum)
                .sum();
        // 323625
    }

    private static int getVal(String[] vals, int x) {
        return Integer.parseInt(vals[x].split("=")[1]);
    }

    private static long getPart2Answer(List<String> lines) {
        return 0;
    }

    private record Rule(String var, String comp, Integer val, String res) {
        public static Rule of(String r) {
            String[] rul = r.split(":");
            if (rul.length == 1) {
                return new Rule("", "", 0, rul[0]);
            } else {
                if (rul[0].contains(">")) {
                    String[] con = rul[0].split(">");
                    return new Rule(con[0], ">", Integer.parseInt(con[1]), rul[1]);
                } else {
                    String[] con = rul[0].split("<");
                    return new Rule(con[0], "<", Integer.parseInt(con[1]), rul[1]);
                }
            }
        }

        public Function<Shape, String> getFunction() {
            if (Objects.equals(comp, "")) {
                return (Shape shp) -> res;
            } else if (Objects.equals(comp, ">")) {
                return (Shape shp) -> shp.getVal(var) > val ? res : null;
            } else {
                return (Shape shp) -> shp.getVal(var) < val ? res : null;
            }
        }
    }
    private record Shape(int x, int m, int a, int s) {
        public int getVal(String var) {
            return switch (var) {
                case "x" -> x;
                case "m" -> m;
                case "a" -> a;
                case "s" -> s;
                default -> throw new RuntimeException();
            };
        }

        public long sum() {
            return x + m + a + s;
        }

        public Shape applyRule(Rule rule) {
            if (Objects.equals(rule.comp, "")) return this;
            if (Objects.equals(rule.var, "x")) {
                if (Objects.equals(rule.comp, ">")) {

                }
            }
        }
    }
}

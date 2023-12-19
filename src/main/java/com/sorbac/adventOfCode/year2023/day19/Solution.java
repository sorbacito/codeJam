package com.sorbac.adventOfCode.year2023.day19;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

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

        List<String> lines2 = new ArrayList<>();
        while (in2.hasNextLine()) {
            lines2.add(in2.nextLine());
        }
        System.out.println(getPart2Answer(lines2));
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
        long sum = 0;
        List<PairRange> cur = new ArrayList<>();
        cur.add(new PairRange(new ShapeRange(new Range(1, 4000), new Range(1, 4000), new Range(1, 4000), new Range(1, 4000)), "in"));
        while (!cur.isEmpty()) {
            List<PairRange> newPRs = new ArrayList<>();
            for (PairRange pr : cur){
                if (Objects.equals(pr.flow, "A")) {
                    sum += pr.getSum();
                    continue;
                }
                if (Objects.equals(pr.flow, "R")) {
                    continue;
                }
                PairRange curPR = pr;
                for (Rule rule : map.get(pr.flow)) {
                    PairRange newPR;
                    if (Objects.equals(rule.comparator, "")) {
                        newPR = new PairRange(curPR.range, rule.newFlow);
                    } else if (Objects.equals(rule.comparator, ">")) {
                        newPR = curPR.moreThan(rule.value, rule.variableName, rule.newFlow);
                        curPR = curPR.lessThan(rule.value + 1, rule.variableName);
                    } else {
                        newPR = curPR.lessThan(rule.value, rule.variableName, rule.newFlow);
                        curPR = curPR.moreThan(rule.value - 1, rule.variableName);
                    }
                    if (newPR.getSum() > 0) {
                        newPRs.add(newPR);
                    }
                }
            }
            cur = newPRs;
        }
        return sum;
    }

    private record Rule(String variableName, String comparator, Integer value, String newFlow) {
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
            if (Objects.equals(comparator, "")) {
                return (Shape shp) -> newFlow;
            } else if (Objects.equals(comparator, ">")) {
                return (Shape shp) -> shp.getVal(variableName) > value ? newFlow : null;
            } else {
                return (Shape shp) -> shp.getVal(variableName) < value ? newFlow : null;
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

    }

    private record Range(int min, int max) {
        public int getRange() {
            return Math.max(0, max - min + 1);
        }
    }
    private record ShapeRange(Range x, Range m, Range a, Range s) {
    }
    private record PairRange(ShapeRange range, String flow) {
        public PairRange moreThan(int value, String field) {
            return moreThan(value, field, flow);
        }

        public PairRange moreThan(int value, String field, String newFlow) {
            return switch (field) {
                case "x" -> new PairRange(new ShapeRange(new Range(Math.max(range.x.min, value + 1), range.x.max), range.m, range.a, range.s), newFlow);
                case "m" -> new PairRange(new ShapeRange(range.x, new Range(Math.max(range.m.min, value + 1), range.m.max), range.a, range.s), newFlow);
                case "a" -> new PairRange(new ShapeRange(range.x, range.m, new Range(Math.max(range.a.min, value + 1), range.a.max), range.s), newFlow);
                case "s" -> new PairRange(new ShapeRange(range.x, range.m, range.a, new Range(Math.max(range.s.min, value + 1), range.s.max)), newFlow);
                default -> throw new IllegalStateException("Unexpected value: " + field);
            };
        }

        public PairRange lessThan(int value, String field) {
            return lessThan(value, field, flow);
        }

        public PairRange lessThan(int value, String field, String newFlow) {
            return switch (field) {
                case "x" -> new PairRange(new ShapeRange(new Range(range.x.min, Math.min(range.x.max, value - 1)), range.m, range.a, range.s), newFlow);
                case "m" -> new PairRange(new ShapeRange(range.x, new Range(range.m.min, Math.min(range.m.max, value - 1)), range.a, range.s), newFlow);
                case "a" -> new PairRange(new ShapeRange(range.x, range.m, new Range(range.a.min, Math.min(range.a.max, value - 1)), range.s), newFlow);
                case "s" -> new PairRange(new ShapeRange(range.x, range.m, range.a, new Range(range.s.min, Math.min(range.s.max, value - 1))), newFlow);
                default -> throw new IllegalStateException("Unexpected value: " + field);
            };
        }

        public long getSum() {
            return (long) range.x.getRange() * range.m.getRange() * range.a.getRange() * range.s.getRange();
        }
    }
}

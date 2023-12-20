package com.sorbac.adventOfCode.year2023.day20;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day20/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day20/test11.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day20/input1.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day20/test2.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day20/test22.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day20/input2.txt"))));

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
        Map<String, MyModule> map = readInput(lines);

        long highPulses = 0;
        long lowPulses = 0;
        int pushes = 0;
        Map<String, Pair> confResult = new HashMap<>();
        confResult.put("", new Pair(0, 0));
        Map<Integer, String> confPush = new HashMap<>();
        confPush.put(0, "");

        while (pushes < 1000) {
            String initSerialized = map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(entry -> entry.getValue().serialize())
                    .collect(Collectors.joining(" "));

            if (confResult.containsKey(initSerialized)) {
                System.out.println("Cycle found: " + pushes);
                int cycles = 1000 / pushes;
                int rest = 1000 % pushes;
                String cycleRes = confPush.get(pushes);
                String restRes = confPush.get(rest);
                long totalLow = confResult.get(cycleRes).low * cycles + confResult.get(restRes).low;
                long totalHigh = confResult.get(cycleRes).high * cycles + confResult.get(restRes).high;
                return totalHigh*totalLow;
            }


            List<Tuple<String, String, Pulse>> cur = new ArrayList<>();
            cur.add(new Tuple<>("button", "broadcaster", Pulse.LOW));
            while (!cur.isEmpty()) {
                highPulses += cur.stream().filter(tuple -> tuple.pulse == Pulse.HIGH).count();
                lowPulses += cur.stream().filter(tuple -> tuple.pulse == Pulse.LOW).count();
                List<Tuple<String, String, Pulse>> next = new ArrayList<>();
                for (Tuple<String, String, Pulse> tuple : cur) {
                    MyModule module = map.get(tuple.toModule);
                    if (module != null) next.addAll(module.process(tuple.fromModule, tuple.pulse));
                }
                cur = next;
            }

            confResult.put(initSerialized, new Pair(lowPulses, highPulses));
            confPush.put(++pushes, initSerialized);
        }

        Pair pushes_1000 = confResult.get(confPush.get(1000));
        return pushes_1000.low * pushes_1000.high;
    }

    private static long getPart2Answer(List<String> lines) {
        Map<String, MyModule> map = readInput(lines);
        long pushes = 0;
        while (++pushes > 0) {
            List<Tuple<String, String, Pulse>> cur = new ArrayList<>();
            cur.add(new Tuple<>("button", "broadcaster", Pulse.LOW));
            long rxLow = 0;
            while (!cur.isEmpty()) {
                List<Tuple<String, String, Pulse>> next = new ArrayList<>();
                for (Tuple<String, String, Pulse> tuple : cur) {
                    MyModule module = map.get(tuple.toModule);
                    if (module != null) next.addAll(module.process(tuple.fromModule, tuple.pulse));
                }
                List<Tuple<String, String, Pulse>> rxSignals = next.stream()
                        .filter(tuple -> Objects.equals(tuple.toModule, "rx")).toList();
                rxLow += rxSignals.stream().filter(tuple -> tuple.pulse == Pulse.LOW).count();
                if (rxSignals.stream()
                        .map(t -> map.get(t.fromModule))
                        .filter(mod -> mod instanceof Conjunction)
                        .anyMatch(con -> ((Conjunction) con).modulePulses.containsValue(Pulse.HIGH))) {
                    System.out.println("Pushes: " + pushes);
                    System.out.println(rxSignals.stream().map(t -> map.get(t.fromModule).serialize()).collect(Collectors.joining(" ")));
                }

                cur = next;
            }

            if (rxLow == 1) {
                return pushes;
            }
        }
        return -1;
    }

    private static Map<String, MyModule> readInput(List<String> lines) {
        Map<String, MyModule> map = new HashMap<>();
        List<String> conjs = new ArrayList<>();
        lines.forEach(line -> {
            String[] parts = line.split(" -> ");
            String name = parts[0];
            if (name.startsWith("%")) {
                name = name.substring(1);
                List<String> call = Arrays.asList(parts[1].split(", "));
                map.put(name, new FlipFlop(name, call, map));
            } else if (name.startsWith("&")) {
                name = name.substring(1);
                List<String> call = Arrays.asList(parts[1].split(", "));
                map.put(name, new Conjunction(name, call, map));
                conjs.add(name);
            } else if (name.equals("broadcaster")) {
                List<String> call = Arrays.asList(parts[1].split(", "));
                map.put(name, new Broadcaster(name, call, map));
            } else {
                throw new RuntimeException("Unknown module: " + name);
            }
        });

        conjs.forEach(name -> {
            Conjunction conjunction = (Conjunction) map.get(name);
            map.entrySet().stream()
                    .filter(entry -> ((ModuleImpl) entry.getValue()).moduleNames.contains(name))
                    .map(Map.Entry::getKey)
                    .forEach(conjunction::addInput);
        });
        return map;
    }

    interface MyModule {
        List<Tuple<String, String, Pulse>> process(String fromModule, Pulse pulse);
        String serialize();
    }

    static abstract class ModuleImpl implements MyModule {
        final String name;
        final List<String> moduleNames;
        final Map<String, MyModule> map;

        public ModuleImpl(String name, List<String> moduleNames, Map<String, MyModule> map) {
            this.name = name;
            this.moduleNames = moduleNames;
            this.map = map;
        }
    }

    static class Broadcaster extends ModuleImpl {

        public Broadcaster(String name, List<String> moduleNames, Map<String, MyModule> map) {
            super(name, moduleNames, map);
        }

        @Override
        public List<Tuple<String, String, Pulse>> process(String fromModule, Pulse pulse) {
            return moduleNames.stream().map(moduleName -> new Tuple<>(name, moduleName, pulse)).toList();
        }

        @Override
        public String serialize() {
            return name;
        }
    }

    static class FlipFlop extends ModuleImpl {
        private boolean isOn = false;

        public FlipFlop(String name, List<String> moduleNames, Map<String, MyModule> map) {
            super(name, moduleNames, map);
        }

        @Override
        public List<Tuple<String, String, Pulse>> process(String fromModule, Pulse pulse) {
            if (pulse == Pulse.LOW) {
                isOn = !isOn;
                return moduleNames.stream().map(moduleName -> new Tuple<>(name, moduleName, isOn ? Pulse.HIGH : Pulse.LOW)).toList();
            }
            return new ArrayList<>();
        }

        @Override
        public String serialize() {
            return name + "-" + (isOn ? "on" : "off");
        }
    }

    private static class Conjunction extends ModuleImpl {
        final Map<String, Pulse> modulePulses = new HashMap<>();

        public Conjunction(String name, List<String> moduleNames, Map<String, MyModule> map) {
            super(name, moduleNames, map);
        }

        public void addInput(String name) {
            modulePulses.put(name, Pulse.LOW);
        }

        @Override
        public List<Tuple<String, String, Pulse>> process(String fromModule, Pulse pulse) {
            modulePulses.put(fromModule, pulse);
            Pulse pulseToSend = modulePulses.values().stream().allMatch(p -> p == Pulse.HIGH) ? Pulse.LOW : Pulse.HIGH;
            return moduleNames.stream()
                    .map(moduleNames -> new Tuple<>(name, moduleNames, pulseToSend))
                    .toList();
        }

        @Override
        public String serialize() {
            return name + "-"
                    + modulePulses.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(entry -> entry.getKey() + "-" + entry.getValue())
                    .collect(Collectors.joining("-"));
        }
    }

    record Tuple<S, T, U>(S fromModule, T toModule, U pulse) {}

    record Pair(long low, long high) {}

    enum Pulse {
        HIGH, LOW
    }
}

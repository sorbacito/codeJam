package com.sorbac.adventOfCode.year2022.day16;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day16/input.txt";
    public static final String TEST_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day16/test.txt";
    private static final Pattern linePattern = Pattern.compile("Valve ([A-Z]+) has flow rate=(-?\\d+); tunnels? leads? to valves? (.*)$");

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE))));
        Map<String, String[]> valvesGraph = new HashMap<>();
        Map<String, Integer> valvesFlow = new HashMap<>();
        while (in.hasNextLine()) {
            Matcher matcher = linePattern.matcher(in.nextLine());
            if (matcher.matches()) {
                String valveName = matcher.group(1);
                int flowRate = Integer.parseInt(matcher.group(2));
                String[] leadToValves = matcher.group(3).split(", ");
                valvesGraph.put(valveName, leadToValves);
                valvesFlow.put(valveName, flowRate);
            }
        }

        int maxFlow = 0;
        List<Case> steps = new ArrayList<>();
        steps.add(new Case("AA", Set.of("AA"), new HashSet<>(), 0, 30));
        while (steps.size() > 0) {
            List<Case> curSteps = new ArrayList<>();
            steps.sort(Comparator.comparingInt(Case::getSumFlowRate));
            for (Case curCase : steps) {
                for (String posValve : valvesGraph.get(curCase.valve)) {
                    if (!curCase.visited.contains(posValve)) {
                        if (curCase.openValves.contains(posValve)) {
                            Set<String> visited = new HashSet<>(curCase.visited);
                            visited.add(posValve);
                            curSteps.add(new Case(posValve, visited, curCase.openValves, curCase.sumFlowRate, curCase.minutesLeft - 1));
                        } else {
                            Set<String> visited = new HashSet<>(curCase.visited);
                            visited.add(posValve);
                            Set<String> openVals = new HashSet<>(curCase.openValves);
                            openVals.add(posValve);

                            curSteps.add(new Case(posValve, visited, openVals, curCase.sumFlowRate, curCase.minutesLeft - 1));
                            if (curCase.minutesLeft > 1) {
                                int minutesLeft = curCase.minutesLeft - 2;
                                int newSumFlowRate = minutesLeft * valvesFlow.get(posValve) + curCase.sumFlowRate;
                                maxFlow = Math.max(maxFlow, newSumFlowRate);
                                curSteps.add(new Case(posValve, visited, openVals,
                                        newSumFlowRate, minutesLeft));
                            }
                        }
                    }
                }
            }
            steps = curSteps;
        }
        System.out.println(maxFlow);
    }

    @AllArgsConstructor
    @Getter
    static class Case {
        String valve;
        Set<String> visited;
        Set<String> openValves;
        int sumFlowRate;
        int minutesLeft;

    }
}

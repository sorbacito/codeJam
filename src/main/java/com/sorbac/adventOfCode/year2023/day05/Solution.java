package com.sorbac.adventOfCode.year2023.day05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day05/test1.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day05/input1.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day05/test2.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day05/input2.txt"))));

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
        long part2Answer = getPart2Answer(lines2);
        System.out.println(part2Answer);
    }

    private static long getPart1Answer(List<String> lines) {
        List<Long> seeds = Arrays.stream(lines.get(0).split(": ")[1].split(" "))
                .mapToLong(Long::parseLong).boxed().toList();
        long minLoc = Long.MAX_VALUE;
        Map<String, String> mappings = new HashMap<>();
        Map<String, List<Range>> ranges = new HashMap<>();
        boolean mappingLine = true;
        String rangeName = "";
        for (int row = 2; row < lines.size(); row++) {
            if (mappingLine) {
                String[] mapping = lines.get(row).split(" ")[0].split("-to-");
                mappings.put(mapping[0], mapping[1]);
                rangeName = mapping[0];
                mappingLine = false;
            } else {
                if (lines.get(row).isEmpty()) {
                    mappingLine = true;
                } else {
                    List<Long> myR = Arrays.stream(lines.get(row).split(" ")).mapToLong(Long::parseLong).boxed().toList();
                    List<Range> rL = ranges.computeIfAbsent(rangeName, k -> new ArrayList<>());
                    rL.add(new Range(myR.get(1), myR.get(0) - myR.get(1), myR.get(2)));
                }
            }
        }

        for (long seed : seeds) {
            minLoc = Math.min(minLoc, getSeedLocation(seed, mappings, ranges));
        }

        return minLoc;
    }

    private static long getSeedLocation(long seed, Map<String, String> mappings, Map<String, List<Range>> ranges) {
        long id = seed;
        String name = "seed";
        while (true) {
            Range finR = new Range(id, 0, 1);
            for (Range r : ranges.get(name)) {
                if (r.withinRange(id)) {
                    finR = r;
                    break;
                }
            }
            id = finR.map(id);
            name = mappings.get(name);
            if (name.equals("location")) {
                return id;
            }
        }
    }

    private static long getSeedForLocation(long location, Map<String, String> mappings, Map<String, List<Range>> ranges) {
        long id = location;
        String name = "location";
        while (true) {
            Range finR = new Range(id, 0, 1);
            for (Range r : ranges.get(name)) {
                if (r.withinRange(id)) {
                    finR = r;
                    break;
                }
            }
            id = finR.map(id);
            name = mappings.get(name);
            if (name.equals("seed")) {
                return id;
            }
        }
    }

    private static long getPart2Answer(List<String> lines) {
        List<Long> seedRangesIn = Arrays.stream(lines.get(0).split(": ")[1].split(" "))
                .mapToLong(Long::parseLong).boxed().toList();
        List<Range> seedRanges = new ArrayList<>();
        for (int i = 0; i < seedRangesIn.size() / 2; ) {
            seedRanges.add(new Range(seedRangesIn.get(i), 0, seedRangesIn.get(i + 1)));
            i += 2;
        }
        Map<String, String> mappings = new HashMap<>();
        Map<String, List<Range>> ranges = new HashMap<>();
        boolean mappingLine = true;
        String rangeName = "";
        for (int row = 2; row < lines.size(); row++) {
            if (mappingLine) {
                String[] mapping = lines.get(row).split(" ")[0].split("-to-");
                mappings.put(mapping[1], mapping[0]);
                rangeName = mapping[1];
                mappingLine = false;
            } else {
                if (lines.get(row).isEmpty()) {
                    mappingLine = true;
                } else {
                    List<Long> myR = Arrays.stream(lines.get(row).split(" ")).mapToLong(Long::parseLong).boxed().toList();
                    List<Range> rL = ranges.computeIfAbsent(rangeName, k -> new ArrayList<>());
                    rL.add(new Range(myR.get(0), myR.get(1) - myR.get(0), myR.get(2)));
                }
            }
        }

        long loc = 0L;
        while (true) {
            long seed = getSeedForLocation(loc, mappings, ranges);
            for (Range seedR : seedRanges) {
                if (seedR.withinRange(seed)) {
                    return loc;
                }
            }
            loc++;
        }
    }

    private record Range(long source, long diff, long length) {
        public boolean withinRange(long num) {
            return num >= source && num < source + length;
        }

        public long map(long num) {
            return num + diff;
        }
    }
}

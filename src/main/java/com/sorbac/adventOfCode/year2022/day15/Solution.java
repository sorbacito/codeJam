package com.sorbac.adventOfCode.year2022.day15;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Solution {
    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day15/input.txt";
    public static final String TEST_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day15/test.txt";
    private static final Pattern linePattern = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");

    private final List<CaveSensor> sensors;
    private final Map<Integer, Set<CaveBeacon>> beaconsByLine;

    public Solution(List<CaveSensor> sensors, Map<Integer, Set<CaveBeacon>> beaconsByLine) {
        this.sensors = sensors;
        this.beaconsByLine = beaconsByLine;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
//        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE))));
        List<CaveSensor> sensors = new ArrayList<>();
        Map<Integer, List<CaveBeacon>> beaconsByLine = new HashMap<>();
        while (in.hasNext()) {
            in.useDelimiter("Sensor at x=");
            in.next();
            int sX = in.nextInt();
            in.next(", ");
            int sY = in.nextInt();
            in.next(": closest beacon is at x=");
            int bX = in.nextInt();
            in.next(", y=");
            int bY = in.nextInt();
            CaveBeacon beacon = new CaveBeacon(bX, bY);
            beaconsByLine.merge(bY, List.of(beacon), (orig, newB) -> {
                ArrayList<CaveBeacon> caveBeacons = new ArrayList<>();
                caveBeacons.addAll(orig);
                caveBeacons.addAll(newB);
                return caveBeacons;
            });
            sensors.add(new CaveSensor(sX, sY, beacon));
        }
        System.out.println(new Solution(sensors, beaconsByLine).getLineCoverage(10));
    }

    private int getLineCoverage(int line) {
        Set<Integer> positions = new HashSet<>();
        sensors.forEach(it -> positions.addAll(it.getCoverage(line)));
        Set<CaveBeacon> caveBeacons = beaconsByLine.get(line);
        if (caveBeacons != null) {
            caveBeacons.forEach(beacon -> positions.remove(beacon.row));
        }
        return positions.size();
    }

    @AllArgsConstructor
    static class CaveSensor {
        final int col;
        final int row;
        final CaveBeacon closestBeacon;

        public Set<Integer> getCoverage(int line) {
            Set<Integer> coverage = new HashSet<>();
            int manhDist = Math.abs(this.col - closestBeacon.col) + Math.abs(this.row - closestBeacon.row);
            int lineRest = manhDist - Math.abs(line - row);
            if (lineRest >= 0)
                IntStream.range(col - lineRest, col + lineRest + 1)
                        .forEach(num -> coverage.add(num));
            return coverage;
        }

        public Optional<Range> getCoverageRange(int line) {
            int manhDist = Math.abs(this.col - closestBeacon.col) + Math.abs(this.row - closestBeacon.row);
            int lineRest = manhDist - Math.abs(line - row);
            if (lineRest >= 0)
                return Optional.of(new Range(col - lineRest, col + lineRest));
            else
                return Optional.empty();
        }
    }

    private static class CaveBeacon {
        final int col;
        final int row;

        CaveBeacon(int col, int row) {
            this.col = col;
            this.row = row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CaveBeacon that = (CaveBeacon) o;

            if (col != that.col) return false;
            return row == that.row;
        }

        @Override
        public int hashCode() {
            int result = col;
            result = 31 * result + row;
            return result;
        }
    }
}

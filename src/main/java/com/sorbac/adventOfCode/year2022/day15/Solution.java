package com.sorbac.adventOfCode.year2022.day15;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day15/input.txt";
    public static final String TEST_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day15/test.txt";

    private final List<CaveSensor> sensors;
    private final Map<Integer, List<CaveBeacon>> beaconsByLine;

    public Solution(List<CaveSensor> sensors, Map<Integer, List<CaveBeacon>> beaconsByLine) {
        this.sensors = sensors;
        this.beaconsByLine = beaconsByLine;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE))));
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

    private boolean getLineCoverage(int i) {
        return false;
    }

    private static class CaveSensor {
        final int col;
        final int row;
        final CaveBeacon beacon;
        private CaveSensor(int sX, int sY, CaveBeacon beacon) {
            this.col = sX;
            this.row = sY;
            this.beacon = beacon;
        }
    }

    private static class CaveBeacon {
        final int col;
        final int row;

        private CaveBeacon(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
}

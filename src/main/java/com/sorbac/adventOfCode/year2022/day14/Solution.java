package com.sorbac.adventOfCode.year2022.day14;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day14/input.txt";
    public static final String TEST_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day14/test.txt";

    private final int[][] cave;

    public Solution(List<List<int[]>> rockLines) {
        this.cave = getCave(rockLines);
    }

    private static int[][] getCave(List<List<int[]>> rockLines) {
        int[][] cave = new int[1000][1000];
        int maxVer = 0;
        int maxHor = 0;
        int minHor = 1000;
        for (List<int[]> line : rockLines) {
            Iterator<int[]> iterator = line.iterator();
            int[] previous = iterator.next();
            maxVer = Math.max(maxVer, previous[1]);
            maxHor = Math.max(maxHor, previous[0]);
            minHor = Math.min(minHor, previous[0]);
            while (iterator.hasNext()) {
                int[] current = iterator.next();
                maxVer = Math.max(maxVer, current[1]);
                maxHor = Math.max(maxHor, current[0]);
                minHor = Math.min(minHor, current[0]);
                if (previous[0] == current[0]) {
                    for (int i = Math.min(previous[1], current[1]); i < Math.max(previous[1], current[1]); i++) {
                        cave[previous[0]][i] = 1;
                    }
                } else {
                    for (int i = Math.min(previous[0], current[0]); i < Math.max(previous[0], current[0]); i++) {
                        cave[i][previous[1]] = 1;
                    }
                }
                previous = current;
            }
        }
        int[][] ints = new int[maxVer + 1][maxHor - minHor + 1];
        for (int x = 0; x < ints.length; x++) {
            for (int y = 0; y < ints[0].length; y++) {
                ints[x][y] = cave[x][minHor + y];
            }
        }
        return ints;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE))));
        int sum = 0;
        int i = 1;
        List<List<int[]>> rockLines = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            List<int[]> rockLine = Arrays.stream(line.split(" -> ")).map(s -> {
                String[] coordinates = s.split(",");
                return new int[]{Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])};
            }).collect(Collectors.toList());
            rockLines.add(rockLine);
        }
        System.out.println(new Solution(rockLines).getAmountOfRestSand());
    }

    public boolean getAmountOfRestSand() {

        return false;
    }

}

package com.sorbac.adventOfCode.year2022.day14;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Data
public class Solution {
    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day14/input.txt";
    public static final String TEST_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day14/test.txt";

    private final int[][] cave;
    private final int stoneMax;

    private static Solution createSolution(List<List<int[]>> rockLines) {
        int limit = 1000;
        int[][] cave = new int[limit][limit];
        int maxHor = 0;
        for (List<int[]> line : rockLines) {
            Iterator<int[]> iterator = line.iterator();
            int[] previous = iterator.next();
            while (iterator.hasNext()) {
                int[] current = iterator.next();
                if (previous[0] == current[0]) { // vertical line
                    for (int i = Math.min(previous[1], current[1]); i <= Math.max(previous[1], current[1]); i++) {
                        cave[i][current[0]] = 1;
                        maxHor = Math.max(maxHor, i);
                    }
                } else { // horizontal line
                    maxHor = Math.max(maxHor, current[1]);
                    for (int i = Math.min(previous[0], current[0]); i <= Math.max(previous[0], current[0]); i++) {
                        cave[current[1]][i] = 1;
                    }
                }
                previous = current;
            }
        }
        return new Solution(cave, maxHor);
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
//        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE))));
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
        Solution solution = createSolution(rockLines);
        System.out.println(solution.getAmountOfRestSand());
    }

    public int getAmountOfRestSand() {
        int sum = 0;
        Grain grain;
        do {
            grain = new Grain(GrainState.MOVED, 500, 0);
            while (grain.state == GrainState.MOVED) {
//                System.out.println("MOVED: [" + grain.x + "," + grain.y + "]");
                moveGrain(grain);
            }
            if (grain.state == GrainState.STUCK) {
//                System.out.println("STUCK: [" + grain.x + "," + grain.y + "]");
                sum++;
            }
        } while (grain.state != GrainState.FELL);
        return sum;
    }

    private void moveGrain(Grain grain) {
        if (grain.y > stoneMax) {
            grain.state = GrainState.FELL;
        } else {
            grain.state = GrainState.MOVED;
            if (cave[grain.y + 1][grain.x] != 1) {
                grain.y++;
            } else if (cave[grain.y + 1][grain.x - 1] != 1) {
                grain.x--;
                grain.y++;
            } else if (cave[grain.y + 1][grain.x + 1] != 1) {
                grain.x++;
                grain.y++;
            } else {
                grain.state = GrainState.STUCK;
                cave[grain.y][grain.x] = 1;
            }
        }
    }

    @Data
    @AllArgsConstructor
    private static class Grain {
        private GrainState state;
        private int x;
        private int y;
    }

    private enum GrainState {
        MOVED, STUCK, FELL;
    }
}

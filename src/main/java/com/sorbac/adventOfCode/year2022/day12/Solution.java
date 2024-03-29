package com.sorbac.adventOfCode.year2022.day12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day12/input.txt";
    public static final String TEST_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day12/test.txt";

    private final List<String> lines;

    public Solution(List<String> lines) {
        this.lines = lines;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
        //Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE))));
        List<String> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }
        System.out.println(new Solution(lines).shortestPathToEnd());
    }

    private int shortestPathToEnd() {
        int sizeX = lines.size();
        int sizeY = lines.get(0).length();
        int[][] steps = initDijkstra(sizeX, sizeY);
        boolean[][] visited = new boolean[sizeX][sizeY];
        Queue<Pair> visit = new PriorityQueue<>();
        Pair start = findChar(sizeX, sizeY, 'S');
        visit.add(start);
        steps[start.x][start.y] = 0;
        while (visit.size() > 0) {
            Pair cur = visit.poll();
            if (!visited[cur.x][cur.y]) {
                List<Pair> adj = new ArrayList<>();
                int newX = cur.x - 1;
                int newY = cur.y;
                if (isValidXY(newX, newY, sizeX, sizeY) && !visited[newX][newY]) adj.add(new Pair(newX, newY, steps[newX][newY]));
                newX = cur.x + 1;
                if (isValidXY(newX, newY, sizeX, sizeY) && !visited[newX][newY]) adj.add(new Pair(newX, newY, steps[newX][newY]));
                newX = cur.x;
                newY = cur.y + 1;
                if (isValidXY(newX, newY, sizeX, sizeY) && !visited[newX][newY]) adj.add(new Pair(newX, newY, steps[newX][newY]));
                newY = cur.y - 1;
                if (isValidXY(newX, newY, sizeX, sizeY) && !visited[newX][newY]) adj.add(new Pair(newX, newY, steps[newX][newY]));
                adj.stream()
                        .filter(p -> getValueAt(cur) + 1 >= getValueAt(p)) // accessible
                        .filter(p -> p.value >= cur.value + 1) // is a shorter way
                        .forEach(p -> {
                            visit.add(new Pair(p.x, p.y, cur.value + 1));
                            steps[p.x][p.y] = cur.value + 1;
                        });
                visited[cur.x][cur.y] = true;
            }
        }
        Pair end = findChar(sizeX, sizeY, 'E');
        return steps[end.x][end.y];
    }

    private boolean isValidXY(int x, int y, int sizeX, int sizeY) {
        return  x >= 0 && x < sizeX && y >= 0 && y < sizeY;
    }

    private char getValueAt(Pair cur) {
        char c = lines.get(cur.x).charAt(cur.y);
        return switch (c) {
            case 'S' -> 'a';
            case 'E' -> 'z';
            default -> c;
        };
    }

    private Pair findChar(int sizeX, int sizeY, char s) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (lines.get(x).charAt(y) == s) {
                    return new Pair(x, y, 0);
                }
            }
        }
        return null;
    }

    private int[][] initDijkstra(int sizeX, int sizeY) {
        int[][] steps = new int[sizeY][sizeY];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                steps[x][y] = Integer.MAX_VALUE;
            }
        }
        return steps;
    }

    private static class Pair implements Comparable<Pair>{
        final int x;
        final int y;
        final int value;

        private Pair(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(value, o.value);
        }
    }
}

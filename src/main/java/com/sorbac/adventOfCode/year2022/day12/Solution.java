package com.sorbac.adventOfCode.year2022.day12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day11/input.txt";
    public static final String TEST_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day11/test.txt";

    private final List<String> lines;

    public Solution(List<String> lines) {
        this.lines = lines;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE))));
        List<String> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }
        System.out.println(new Solution(lines).shortestPathToEnd());
    }

    private boolean shortestPathToEnd() {
        int[][] steps = new int[lines.size()][lines.size()];
        Pair endLocation;
        Stack<Pair> visit = new Stack<>();
        visit.add(new Pair(0, 0));
        while (visit.size() > 0) {
            Pair cur = visit.pop();
            List<Pair> adj = new ArrayList<>();
            Pair left = new Pair(cur.x - 1, cur.y);
            Pair right = new Pair(cur.x + 1, cur.y);
            Pair down = new Pair(cur.x, cur.y - 1);
            Pair up = new Pair(cur.x, cur.y + 1);
            adj.add(left);
            adj.add(right);
            adj.add(down);
            adj.add(up);
            adj.stream()
                    .filter(p -> p.isValid(lines.size()))
                    .filter(p -> lines.get(cur.x).charAt(cur.y) + 1 >= lines.get(p.x).charAt(p.y))
                    .forEach(p -> );
        }
        return steps[endLocation.x][endLocation.y];
    }

    private static class Pair{
        final int x;
        final int y;

        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
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

        public boolean isValid(int size) {
            return  x >= 0 && x < size && y >= 0 && y < size;
        }
    }
}

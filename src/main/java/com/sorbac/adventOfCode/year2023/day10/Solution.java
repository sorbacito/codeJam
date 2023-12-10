package com.sorbac.adventOfCode.year2023.day10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/" + String.join("/", Solution.class.getPackageName().split("\\.")) + "/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/" + String.join("/", Solution.class.getPackageName().split("\\.")) + "/test11.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/" + String.join("/", Solution.class.getPackageName().split("\\.")) + "/input1.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/" + String.join("/", Solution.class.getPackageName().split("\\.")) + "/test2.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/" + String.join("/", Solution.class.getPackageName().split("\\.")) + "/test22.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/" + String.join("/", Solution.class.getPackageName().split("\\.")) + "/test23.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/" + String.join("/", Solution.class.getPackageName().split("\\.")) + "/input2.txt"))));

        List<String> lines1 = new ArrayList<>();
        while (in1.hasNextLine()) {
            lines1.add(in1.nextLine());
        }
        List<Pos> part1Answer = getPart1Answer(lines1);
        System.out.println((part1Answer.size() + 1)/2);

        List<String> lines2 = new ArrayList<>();
        while (in2.hasNextLine()) {
            lines2.add(in2.nextLine());
        }
        long part2Answer = getPart2Answer(lines2);
        System.out.println(part2Answer);
    }

    private static List<Pos> getPart1Answer(List<String> lines) {
        int depth = 0;

        List<List<Pos>> paths = new ArrayList<>();
        Pos start = startPos(lines);
        List<Pos> l = new ArrayList<>();
        l.add(start);
        paths.add(l);

        while (!paths.isEmpty()) {
            List<List<Pos>> newPaths = new ArrayList<>();
            for (List<Pos> curP : paths) {
                Pos lastP = curP.get(curP.size() - 1);
                int row = lastP.row;
                int col = lastP.col;
                for(Side sideLast : findSides(lines, lastP)) {
                    if (sideLast == Side.NORTH) {
                        Pos p = new Pos(row - 1, col);
                        Collection<Side> sidesP = findSides(lines, p);
                        if (sidesP.contains(Side.SOUTH)) {
                            if (depth > 1 && getChar(lines, p) == 'S') {
                                return curP;
                            } else if (!curP.contains(p)) {
                                List<Pos> newP = new ArrayList<>(curP);
                                newP.add(p);
                                newPaths.add(newP);
                            }
                        }
                    }
                    if (sideLast == Side.SOUTH) {
                        Pos p = new Pos(row + 1, col);
                        Collection<Side> sidesP = findSides(lines, p);
                        if (sidesP.contains(Side.NORTH)) {
                            if (depth > 1 && getChar(lines, p) == 'S') {
                                return curP;
                            } else if (!curP.contains(p)) {
                                List<Pos> newP = new ArrayList<>(curP);
                                newP.add(p);
                                newPaths.add(newP);
                            }
                        }
                    }
                    if (sideLast == Side.EAST) {
                        Pos p = new Pos(row, col + 1);
                        Collection<Side> sidesP = findSides(lines, p);
                        if (sidesP.contains(Side.WEST)) {
                            if (depth > 1 && getChar(lines, p) == 'S') {
                                return curP;
                            } else if (!curP.contains(p)) {
                                List<Pos> newP = new ArrayList<>(curP);
                                newP.add(p);
                                newPaths.add(newP);
                            }
                        }
                    }
                    if (sideLast == Side.WEST) {
                        Pos p = new Pos(row, col - 1);
                        Collection<Side> sidesP = findSides(lines, p);
                        if (sidesP.contains(Side.EAST)) {
                            if (depth > 1 && getChar(lines, p) == 'S') {
                                return curP;
                            } else if (!curP.contains(p)) {
                                List<Pos> newP = new ArrayList<>(curP);
                                newP.add(p);
                                newPaths.add(newP);
                            }
                        }
                    }
                }
            }
            depth++;
            paths = newPaths;
        }
        return List.of();
    }

    private static Collection<Pos> findAdjPos(List<String> lines, Pos p) {
        List<Pos> ret = new ArrayList<>();
        int row = p.row();
        int col = p.col();
        if (isValid(row + 1, col, lines)) {
            char c = lines.get(row + 1).charAt(col);
            if (c == '|' || c == 'J' || c == 'L') {
                ret.add(new Pos(row + 1, col));
            }
            char cur = lines.get(row).charAt(col);
            if (c == 'S' &&  cur == '|' || cur == 'L' || cur == 'F') {
                ret.add(new Pos(row + 1, col));
            }
        }
        if (isValid(row - 1, col, lines)) {
            char c = lines.get(row - 1).charAt(col);
            if (c == '|' || c == '7' || c == 'F') {
                ret.add(new Pos(row - 1, col));
            }
            char cur = lines.get(row).charAt(col);
            if (c == 'S' &&  cur == '|' || cur == 'F' || cur == '7') {
                ret.add(new Pos(row - 1, col));
            }
        }
        if (isValid(row, col + 1, lines)) {
            char c = lines.get(row).charAt(col + 1);
            if (c == '-' || c == '7' || c == 'J') {
                ret.add(new Pos(row, col + 1));
            }
            char cur = lines.get(row).charAt(col);
            if (c == 'S' &&  cur == '-' || cur == 'J' || cur == 'L') {
                ret.add(new Pos(row, col + 1));
            }
        }
        if (isValid(row, col - 1, lines)) {
            char c = lines.get(row).charAt(col - 1);
            if (c == '-' || c == 'L' || c == 'F') {
                ret.add(new Pos(row, col - 1));
            }
            char cur = lines.get(row).charAt(col);
            if (c == 'S' &&  cur == '-' || cur == 'J' || cur == 'L') {
                ret.add(new Pos(row, col - 1));
            }
        }
        return ret;
    }

    private static char getChar(List<String> lines, Pos pos) {
        return lines.get(pos.row).charAt(pos.col);
    }

    private static Collection<Side> findSides(List<String> lines, Pos pos) {
        return findSides(lines, pos.row, pos.col);
    }
    private static Collection<Side> findSides(List<String> lines, int row, int col) {
        char c = lines.get(row).charAt(col);
        if (isValid(row, col, lines) && c != '.') {
            if (c == 'S') {
                return List.of(Side.values());
            }
            if (c == '|') {
                return List.of(Side.NORTH, Side.SOUTH);
            }
            if (c == '-') {
                return List.of(Side.EAST, Side.WEST);
            }
            if (c == 'L') {
                return List.of(Side.EAST, Side.NORTH);
            }
            if (c == 'J') {
                return List.of(Side.WEST, Side.NORTH);
            }
            if (c == '7') {
                return List.of(Side.WEST, Side.SOUTH);
            }
            if (c == 'F') {
                return List.of(Side.EAST, Side.SOUTH);
            }
        }
        return new ArrayList<>();
    }

    private enum Side {
        NORTH,
        EAST,
        SOUTH,
        WEST;
    }

    private static boolean isValid(int row, int col, List<String> lines) {
        return row >= 0 && col >= 0 && row < lines.size() && col < lines.get(row).length();
    }

    private static Pos startPos(List<String> lines) {
        int row = 0;
        for (String line : lines) {
            int col = line.indexOf('S');
            if (col != -1) {
                System.out.println(new Pos(row, col));
                return new Pos(row, col);
            }
            row++;
        }
        return null;
    }

    private static long getPart2Answer(List<String> lines) {
        long sum = 0;

        List<Pos> loop = getPart1Answer(lines);
        List<Pos> outside = new ArrayList<>();
        int cols = lines.get(0).length();
        int rows = lines.size();
        Queue<Pos> startPos = new LinkedList<>(IntStream.range(0, cols).mapToObj(i -> new Pos(0, i)).toList());
        startPos.addAll(IntStream.range(0, cols).mapToObj(i -> new Pos(rows - 1, i)).toList());
        startPos.addAll(IntStream.range(0, rows).mapToObj(i -> new Pos(i, 0)).toList());
        startPos.addAll(IntStream.range(0, rows).mapToObj(i -> new Pos(i, cols - 1)).toList());
        while (!startPos.isEmpty()) {
            Pos pos = startPos.poll();
            if (!loop.contains(pos) && !outside.contains(pos)) {
                outside.add(pos);
            }
            int row = pos.row;
            int col = pos.col;
            if (isValid(row + 1, col, lines)) {
                Pos newP = new Pos(row + 1, col);
                if (!loop.contains(newP) && !outside.contains(newP)) {
                    outside.add(newP);
                    startPos.add(newP);
                }
            }
            if (isValid(row - 1, col, lines)) {
                Pos newP = new Pos(row - 1, col);
                if (!loop.contains(newP) && !outside.contains(newP)) {
                    outside.add(newP);
                    startPos.add(newP);
                }
            }
            if (isValid(row, col + 1, lines)) {
                Pos newP = new Pos(row, col + 1);
                if (!loop.contains(newP) && !outside.contains(newP)) {
                    outside.add(newP);
                    startPos.add(newP);
                }
            }
            if (isValid(row, col - 1, lines)) {
                Pos newP = new Pos(row, col - 1);
                if (!loop.contains(newP) && !outside.contains(newP)) {
                    outside.add(newP);
                    startPos.add(newP);
                }
            }
        }


        return rows*cols - outside.size() - loop.size();
    }

    private record Pos(int row, int col) implements Comparable<Pos> {

        @Override
        public int compareTo(Pos o) {
            int comRow = Integer.compare(row, o.row);
            int comCol = Integer.compare(col, o.col);
            return comRow == 0 ? comCol : comRow;
        }
    }

}

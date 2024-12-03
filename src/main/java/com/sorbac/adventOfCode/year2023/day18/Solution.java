package com.sorbac.adventOfCode.year2023.day18;

import com.sorbac.adventOfCode.common.Converter;
import com.sorbac.adventOfCode.common.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/test11.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/input1.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/test2.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/input2.txt"))));

//        List<String> lines1 = new ArrayList<>();
//        while (in1.hasNextLine()) {
//            lines1.add(in1.nextLine());
//        }
//        long part1Answer = getPart1Answer(lines1);
//        System.out.println(part1Answer);

        List<String> lines2 = new ArrayList<>();
        while (in2.hasNextLine()) {
            lines2.add(in2.nextLine());
        }
        System.out.println(getPart2Answer(lines2));
    }

    private static long getPart1Answer(List<String> lines) {
        int row = 0;
        int col = 0;
        List<Pos> hole = new ArrayList<>();
        for(String l : lines) {
            String[] s = l.split(" ");
            String dir = s[0];
            int len = Integer.parseInt(s[1]);
            if (dir.equals("D")) {
                int myR = row;
                int myC = col;
                hole.addAll(IntStream.rangeClosed(1, len).mapToObj(dif -> new Pos(myR - dif, myC)).toList());
                row -= len;
            }
            if (dir.equals("U")) {
                int myR = row;
                int myC = col;
                hole.addAll(IntStream.rangeClosed(1, len).mapToObj(dif -> new Pos(myR + dif, myC)).toList());
                row += len;
            }
            if (dir.equals("R")) {
                int myR = row;
                int myC = col;
                hole.addAll(IntStream.rangeClosed(1, len).mapToObj(dif -> new Pos(myR, myC + dif)).toList());
                col += len;
            }
            if (dir.equals("L")) {
                int myR = row;
                int myC = col;
                hole.addAll(IntStream.rangeClosed(1, len).mapToObj(dif -> new Pos(myR, myC - dif)).toList());
                col -= len;
            }
        }
        Queue<Pos> visit = new LinkedList<>();
        visit.add(new Pos(-1, 1));
        while (!visit.isEmpty()) {
            Pos p = visit.poll();
            if (!hole.contains(p)) {
                hole.add(p);
                List<Pos> notV = new ArrayList<>();
                Stream.of(-1, 1)
                        .forEach(diff -> {
                            Pos newP = new Pos(p.y + diff, p.x);
                            if (!hole.contains(newP)) {
                                notV.add(newP);
                            }
                        });
                Stream.of(-1, 1)
                        .forEach(d -> {
                            Pos newP = new Pos(p.y, p.x + d);
                            if (!hole.contains(newP)) {
                                notV.add(newP);
                            }
                        });
                visit.addAll(notV);
            }
        }
        return hole.size();
        //answer: 58550
    }

    private static long getPart2Answer(List<String> lines) {
        List<Pos> positions = new ArrayList<>();
        Pos cur = new Pos(0, 0);
        for (String line : lines) {
            String[] s = line.split(" ");
            String hexVal = s[2].split("\\)")[0].split("\\(#")[1];
            char dir = hexVal.charAt(hexVal.length() - 1);
            int len = Converter.hexToInteger(hexVal.substring(0, hexVal.length() - 1));
            if (dir == '2') { // L
                cur = new Pos(cur.y, cur.x - len);
            }
            if (dir == '3') { // U
                cur = new Pos(cur.y - len, cur.x);
            }
            if (dir == '0') { // R
                cur = new Pos(cur.y, cur.x + len);
            }
            if (dir == '1') { // D
                cur = new Pos(cur.y + len, cur.x);
            }
            positions.add(cur);
        }

        return calculateArea(positions);
    }

    private static long calculateArea(List<Pos> positions) {
        List<Pair<Pos, Pos>> pairList = IntStream.range(0, positions.size())
                .mapToObj(i -> new Pair<>(positions.get(i), positions.get((i + 1) % positions.size()))).toList();
        long around = pairList.stream()
                .mapToLong(p -> Math.abs(p.left().y - p.right().y) + Math.abs(p.left().x - p.right().x))
                .sum();
        long inside = pairList.stream()
                .mapToLong(p -> (long) (p.left().y + p.right().y) * (p.left().x - p.right().x))
                .sum();
        return around / 2 + Math.abs(inside) / 2 + 1;
    }

    private record Pos(Integer y, Integer x) {
    }
}

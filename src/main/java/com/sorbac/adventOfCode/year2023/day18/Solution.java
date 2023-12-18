package com.sorbac.adventOfCode.year2023.day18;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/test11.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/input1.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/test2.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/test22.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day18/input2.txt"))));

        List<String> lines1 = new ArrayList<>();
        while (in1.hasNextLine()) {
            lines1.add(in1.nextLine());
        }
        long part1Answer = getPart1Answer(lines1);
        System.out.println(part1Answer);

//        List<String> lines2 = new ArrayList<>();
//        while (in2.hasNextLine()) {
//            lines2.add(in2.nextLine());
//        }
//        System.out.println(getPart2Answer(lines2));
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
                            Pos newP = new Pos(p.row + diff, p.col);
                            if (!hole.contains(newP)) {
                                notV.add(newP);
                            }
                        });
                Stream.of(-1, 1)
                        .forEach(d -> {
                            Pos newP = new Pos(p.row, p.col + d);
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
        return 0;
    }

    private record Pos(Integer row, Integer col) {}
}

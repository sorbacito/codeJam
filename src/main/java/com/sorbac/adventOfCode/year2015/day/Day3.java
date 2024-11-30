package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.common.Loc;
import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.HashSet;
import java.util.Set;

public class Day3 extends Day2015 {
    private static final int DAY_NR = 3;

    protected Day3() {
        super(DAY_NR);
    }

    protected Day3(String input) {
        super(DAY_NR, input);
    }

    public static void main(String[] args) {
        new Day3(">").printParts(2);
        new Day3("^v").printParts(2, 3);
        new Day3("^>v<").printParts(4, 3);
        new Day3("^v^v^v^v^v").printParts(2, 11);
        new Day3().printParts(2081, 2341);
    }

    @Override
    protected Object part1() {
        Loc cur = new Loc(0, 0);
        Set<Loc> visited = new HashSet<>();
        visited.add(cur);
        for (char c : day().toCharArray()) {
            cur = getNextLoc(c, cur);
            visited.add(cur);
        }
        return visited.size();
    }

    @Override
    protected Object part2() {
        Loc curSanta = new Loc(0, 0);
        Loc curRobo = new Loc(0, 0);
        Set<Loc> visited = new HashSet<>();
        visited.add(curSanta);
        for (int i = 0; i < day().toCharArray().length; i++) {
            char c = day().charAt(i);
            if (i % 2 == 0) {
                curSanta = getNextLoc(c, curSanta);
                visited.add(curSanta);
            } else {
                curRobo = getNextLoc(c, curRobo);
                visited.add(curRobo);
            }
        }
        return visited.size();
    }

    private static Loc getNextLoc(char c, Loc loc) {
        return switch (c) {
            case '^' -> loc.up();
            case 'v' -> loc.down();
            case '<' -> loc.left();
            case '>' -> loc.right();
            default -> throw new IllegalArgumentException("Unknown direction: " + c);
        };
    }
}

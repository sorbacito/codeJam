package com.sorbac.adventOfCode.year2016.day;


import com.sorbac.adventOfCode.common.Direction;
import com.sorbac.adventOfCode.common.Loc;
import com.sorbac.adventOfCode.year2016.Day2016;

import java.util.HashSet;
import java.util.Set;

public class Day1 extends Day2016 {
    private static final int DAY = Integer.parseInt(Day1.class.getSimpleName().replaceFirst("Day", ""));

    protected Day1() {
        super(DAY);
    }

    protected Day1(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day1("R2, L3").printParts(5);
        new Day1("R2, R2, R2").printParts(2);
        new Day1("R5, L5, R5, R3").printParts(12);
        new Day1().printParts(300, 159);
    }

    @Override
    protected Object part1() {
        Loc start = new Loc(0, 0);
        Loc end = new Loc(0, 0);
        Direction direction = Direction.UP;
        for (String step : day().split(", ")) {
            direction = step.charAt(0) == 'R' ? direction.turnRight() : direction.turnLeft();
            end = end.move(direction, Integer.parseInt(step.substring(1)));
        }
        return start.manhattanDistance(end);
    }

    @Override
    protected Object part2() {
        Loc start = new Loc(0, 0);
        Loc end = new Loc(0, 0);
        Set<Loc> visited = new HashSet<>();
        Direction direction = Direction.UP;
        for (String step : day().split(", ")) {
            direction = step.charAt(0) == 'R' ? direction.turnRight() : direction.turnLeft();
            int steps = Integer.parseInt(step.substring(1));
            for (int i = 0; i < steps; i++) {
                end = end.move(direction, 1);
                if (visited.contains(end)) {
                    return start.manhattanDistance(end);
                }
                visited.add(end);
            }
        }
        return -1;
    }
}

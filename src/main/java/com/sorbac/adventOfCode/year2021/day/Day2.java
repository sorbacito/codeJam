package com.sorbac.adventOfCode.year2021.day;

import com.sorbac.adventOfCode.common.Loc;
import com.sorbac.adventOfCode.year2021.Day2021;

import java.util.List;

public class Day2 extends Day2021 {
    private static final int DAY = Integer.parseInt(Day2.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2() {
        super(DAY);
    }

    protected Day2(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day2().printParts();
        new Day2("forward 5\n" +
                "down 5\n" +
                "forward 8\n" +
                "up 3\n" +
                "down 8\n" +
                "forward 2").printPart1(150L);
//        new Day2("test input 2").printPart1("expected result 2");
    }

    @Override
    protected Object part1() {
        Loc loc = new Loc(0, 0);
        List<String> commands = dayStreamLines().toList();
        for (String command : commands) {
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "forward":
                    loc = loc.left(Integer.parseInt(parts[1]));
                    break;
                case "up":
                    loc = loc.down(Integer.parseInt(parts[1]));
                    break;
                case "down":
                    loc = loc.up(Integer.parseInt(parts[1]));
                    break;
            }
        }
        return loc.x() * loc.y();
    }

    @Override
    protected Object part2() {
        Loc loc = new Loc(0, 0);
        List<String> commands = dayStreamLines().toList();
        int aim = 0;
        for (String command : commands) {
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "forward":
                    loc = loc.left(Integer.parseInt(parts[1]));
                    loc = loc.up(aim * Integer.parseInt(parts[1]));
                    break;
                case "up":
                    aim -= Integer.parseInt(parts[1]);
                    break;
                case "down":
                    aim += Integer.parseInt(parts[1]);
                    break;
            }
        }
        return loc.x() * loc.y();
    }
}

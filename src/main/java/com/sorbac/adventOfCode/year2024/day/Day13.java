package com.sorbac.adventOfCode.year2024.day;

import com.sorbac.adventOfCode.common.Loc;
import com.sorbac.adventOfCode.common.Tuple;
import com.sorbac.adventOfCode.year2024.Day2024;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 extends Day2024 {
    private static final int DAY = Integer.parseInt(Day13.class.getSimpleName().replaceFirst("Day", ""));

    protected Day13() {
        super(DAY);
    }

    protected Day13(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day13().printParts(37128L, 74914228471331L);
        new Day13(testInput1()).printPart1(480L);
        new Day13(testInput2()).printPart2(875318608908L);
    }

    @Override
    protected Object part1() {
        List<Tuple<Loc, Loc, Loc>> games = Arrays.stream(day().split("\n\n")).map(s -> gameInput(s, 0)).toList();
        List<Long> costs = games.stream().mapToLong(this::calculateCost)
                .filter(Objects::nonNull).boxed().toList();
        return costs.stream().mapToLong(Long::valueOf).sum();
    }

    @Override
    protected Object part2() {
        List<Tuple<Loc, Loc, Loc>> games = Arrays.stream(day().split("\n\n")).map(s -> gameInput(s, 10000000000000L)).toList();
        List<Long> costs = games.stream().mapToLong(this::calculateCost)
                .filter(Objects::nonNull).boxed().toList();
        return costs.stream().mapToLong(Long::valueOf).sum();
    }

    private Long calculateCost(Tuple<Loc, Loc, Loc> game) {
        Loc buttonA = game.getA();
        Loc buttonB = game.getB();
        Loc prize = game.getC();

        long d = (buttonA.x() * buttonB.y()) - (buttonB.x() * buttonA.y());
        long dX = (prize.x() * buttonB.y()) - (buttonB.x() * prize.y());
        long dY = (buttonA.x() * prize.y()) - (prize.x() * buttonA.y());

        if (dX % d == 0 && dY % d == 0) {
            long pushA = dX / d;
            long pushB = dY / d;
            return pushA * 3 + pushB;
        }
        return 0L;
    }

    private Tuple<Loc, Loc, Loc> gameInput(String s, long additional) {
        String[] lines = s.split("\n");
        Pattern patternA = Pattern.compile("Button A: X\\+(\\d+), Y\\+(\\d+)");
        Pattern patternB = Pattern.compile("Button B: X\\+(\\d+), Y\\+(\\d+)");
        Pattern patternPrize = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
        Matcher matcherA = patternA.matcher(lines[0]);
        matcherA.find();
        Loc buttonA = new Loc(Integer.parseInt(matcherA.group(1)), Integer.parseInt(matcherA.group(2)));
        Matcher matcherB = patternB.matcher(lines[1]);
        matcherB.find();
        Loc buttonB = new Loc(Integer.parseInt(matcherB.group(1)), Integer.parseInt(matcherB.group(2)));
        Matcher matcherPrize = patternPrize.matcher(lines[2]);
        matcherPrize.find();
        Loc prize = new Loc(Integer.parseInt(matcherPrize.group(1)) + additional, Integer.parseInt(matcherPrize.group(2)) + additional);
        return new Tuple<>(buttonA, buttonB, prize);
    }

    private static String testInput1() {
        return "Button A: X+94, Y+34\n" +
                "Button B: X+22, Y+67\n" +
                "Prize: X=8400, Y=5400\n" +
                "\n" +
                "Button A: X+26, Y+66\n" +
                "Button B: X+67, Y+21\n" +
                "Prize: X=12748, Y=12176\n" +
                "\n" +
                "Button A: X+17, Y+86\n" +
                "Button B: X+84, Y+37\n" +
                "Prize: X=7870, Y=6450\n" +
                "\n" +
                "Button A: X+69, Y+23\n" +
                "Button B: X+27, Y+71\n" +
                "Prize: X=18641, Y=10279";
    }

    private static String testInput2() {
        return testInput1();
    }
}

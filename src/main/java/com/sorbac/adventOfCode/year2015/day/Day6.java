package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.common.Loc;
import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.List;
import java.util.function.Function;

public class Day6 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day6.class.getSimpleName().replaceFirst("Day", ""));
    private static final char OFF = 0;
    private static final char ON = 'n';

    protected Day6() {
        super(DAY);
    }

    protected Day6(String input) {
        super(DAY, input);
    }

    protected Day6(String... lines) {
        super(DAY, String.join(DEFAULT_DELIMITER, lines));
    }

    public static void main(String[] args) {
        new Day6("turn on 0,0 through 999,999").printParts(1000L * 1000L);
        new Day6("turn on 0,0 through 499,0", "toggle 0,0 through 999,0").printParts(500L);
        new Day6("turn on 0,0 through 999,999", "turn off 499,499 through 500,500").printParts(1000L * 1000L - 4L);

        System.out.println("\nPart2");
        new Day6("turn on 0,0 through 0,0").printPart2(1);
        new Day6("toggle 0,0 through 999,999").printPart2(2000000);

        System.out.println("\nSolution:");
        new Day6().printParts(569999L, 17836115);
    }

    @Override
    protected Object part1() {
        Grid grid = new Day6.GridPart1(1000, 1000);
        List<Instruction> instructions = dayStreamLines().map(Instruction::parse).toList();
        instructions.forEach(grid::apply);
        return grid.count(ON);
    }

    @Override
    protected Object part2() {
        Grid grid = new Day6.GridPart2(1000, 1000);
        List<Instruction> instructions = dayStreamLines().map(Instruction::parse).toList();
        instructions.forEach(grid::apply);
        return grid.getCharStream().sum();
    }

    private record Instruction(Action action, Loc start, Loc end) {
        public static Instruction parse(String instruction) {
            Action action = Action.parse(instruction);
            String[] split = instruction.split(" ");
            Loc start = Loc.parse(split[split.length - 3]);
            Loc end = Loc.parse(split[split.length - 1]);
            return new Instruction(action, start, end);
        }
    }

    private enum Action {
        ON, OFF, TOGGLE;

        public static Action parse(String action) {
            if (action.startsWith("turn on")) return ON;
            if (action.startsWith("turn off")) return OFF;
            if (action.startsWith("toggle")) return TOGGLE;
            throw new IllegalArgumentException("Unknown action: " + action);
        }
    }

    private static class GridPart1 extends Day6.Grid {
        public GridPart1(int width, int height) {
            super(width, height, OFF);
        }

        @Override
        public Function<Loc, Character> onFunction() {
            return loc -> ON;
        }

        @Override
        public Function<Loc, Character> offFunction() {
            return loc -> OFF;
        }

        @Override
        public Function<Loc, Character> toggleFunction() {
            return loc -> get(loc) == OFF ? ON : OFF;
        }
    }

    private static class GridPart2 extends Day6.Grid {
        public GridPart2(int width, int height) {
            super(width, height, Character.MIN_VALUE);
        }

        @Override
        public Function<Loc, Character> onFunction() {
            return loc -> (char) (get(loc) + 1);
        }

        @Override
        public Function<Loc, Character> offFunction() {
            return loc -> get(loc) > 0 ? (char) (get(loc) - 1) : 0;
        }

        @Override
        public Function<Loc, Character> toggleFunction() {
            return loc -> (char) (get(loc) + 2);
        }
    }

    private abstract static class Grid extends com.sorbac.adventOfCode.common.Grid {
        public Grid(int width, int height, Character defaultValue) {
            super(width, height, defaultValue);
        }

        public void apply(Instruction instruction) {
            switch (instruction.action) {
                case ON -> applyOn(instruction);
                case OFF -> applyOff(instruction);
                case TOGGLE -> applyToggle(instruction);
            }
        }

        private void applyOn(Instruction instruction) {
            this.apply(instruction.start, instruction.end, onFunction());
        }

        public abstract Function<Loc, Character> onFunction();

        private void applyOff(Instruction instruction) {
            this.apply(instruction.start, instruction.end, offFunction());
        }

        public abstract Function<Loc, Character> offFunction();

        private void applyToggle(Instruction instruction) {
            this.apply(instruction.start, instruction.end, toggleFunction());
        }

        public abstract Function<Loc, Character> toggleFunction();
    }
}

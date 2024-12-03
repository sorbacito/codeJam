package com.sorbac.adventOfCode.year2016.day;


import com.sorbac.adventOfCode.common.Grid;
import com.sorbac.adventOfCode.common.Loc;
import com.sorbac.adventOfCode.year2016.Day2016;

import java.util.function.Predicate;

public class Day2 extends Day2016 {
    private static final int DAY = Integer.parseInt(Day2.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2() {
        super(DAY);
    }

    protected Day2(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day2("ULL\n" +
                "RRDDD\n" +
                "LURDL\n" +
                "UUUUD").printParts("1985");

        new Day2().printParts("69642", "8CB23");
    }

    @Override
    protected Object part1() {
        Loc loc = new Loc(1, 1);
        Grid grid = new Grid(new char[][]{
                "123".toCharArray(),
                "456".toCharArray(),
                "789".toCharArray(),
        });
        return solvePart(loc, grid);
    }

    private String solvePart(Loc loc, Grid grid) {
        StringBuilder code = new StringBuilder();
        Predicate<Loc> gridPredicate = gridPredicate(grid);
        for (String line : dayStreamLines().toList()) {
            for (char step : line.toCharArray()) {
                switch (step) {
                    case 'U':
                        if (gridPredicate.test(loc.up())) loc = loc.up();
                        break;
                    case 'D':
                        if (gridPredicate.test(loc.down())) loc = loc.down();
                        break;
                    case 'L':
                        if (gridPredicate.test(loc.left())) loc = loc.left();
                        break;
                    case 'R':
                        if (gridPredicate.test(loc.right())) loc = loc.right();
                        break;
                }
            }
            code.append(grid.get(loc));
        }
        return code.toString();
    }

    public static Predicate<Loc> predicatePart1() {
        return loc -> loc.row() > 0 && loc.row() <= 3 && loc.col() > 0 && loc.col() <= 3;
    }

    @Override
    protected Object part2() {
        Grid grid = new Grid(new char[][]{
                "  1  ".toCharArray(),
                " 234 ".toCharArray(),
                "56789".toCharArray(),
                " ABC ".toCharArray(),
                "  D  ".toCharArray(),
        });
        Loc start = new Loc(2, 0);
        Predicate<Loc> inKeyPadPredicate = gridPredicate(grid);
        return solvePart(start, grid);
    }

    private Predicate<Loc> gridPredicate(Grid grid) {
        return loc -> !grid.isOutOfBounds(loc) && grid.get(loc) != ' ';
    }
}

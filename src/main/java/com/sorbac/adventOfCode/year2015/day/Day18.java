package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.common.Grid;
import com.sorbac.adventOfCode.common.Loc;
import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Day18 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day18.class.getSimpleName().replaceFirst("Day", ""));
    public static final char ON = '#';
    public static final char OFF = '.';

    protected Day18() {
        super(DAY);
    }

    protected Day18(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day18().setExample(1).setArgument(4).printPart1(4);
        new Day18().setExample(2).setArgument(5).printPart2(17);
        new Day18().setArgument(100).printParts(1061, 1006);
    }

    @Override
    protected Object part1() {
        Function<Grid, Function<Loc, Character>> mapping = (grid) -> {
            Grid curGrid = grid.copy();
            return loc -> {
                if (curGrid.get(loc) == ON) {
                    if (countNeighbours(curGrid, loc) == 2 || countNeighbours(curGrid, loc) == 3) {
                        return ON;
                    }
                    return OFF;
                } else {
                    if (countNeighbours(curGrid, loc) == 3) {
                        return ON;
                    }
                    return OFF;
                }
            };
        };
        return solvePart(mapping);
    }

    @Override //TODO: solve
    protected Object part2() {
        Function<Grid, Function<Loc, Character>> mapping = (grid) -> {
            Grid curGrid = grid.copy();
            return loc -> {
                if (List.of(new Loc(0, 0), new Loc(0, curGrid.width() - 1),
                                new Loc(curGrid.height() - 1, 0), new Loc(curGrid.height() - 1, curGrid.width() - 1))
                        .contains(loc)) return ON;
                if (curGrid.get(loc) == ON) {
                    if (countNeighbours(curGrid, loc) == 2 || countNeighbours(curGrid, loc) == 3) {
                        return ON;
                    }
                    return OFF;
                } else {
                    if (countNeighbours(curGrid, loc) == 3) {
                        return ON;
                    }
                    return OFF;
                }
            };
        };
        return solvePart(mapping);
    }

    private int solvePart(Function<Grid, Function<Loc, Character>> partFunction) {
        Grid grid = new Grid(day());

        for (int i = 0; i < (int) getArgument(); i++) {
            grid.apply(partFunction.apply(grid));
        }
        return (int) grid.count(ON);
    }


    private int countNeighbours(Grid grid, Loc loc) {
        Loc up = loc.up();
        Loc down = loc.down();
        List<Loc> list = Stream.of(up, down, loc.left(), loc.right(),
                up.left(), up.right(),
                down.left(), down.right()).filter(l -> !grid.isOutOfBounds(l) && grid.get(l) == ON).toList();
        return list.size();
    }
}

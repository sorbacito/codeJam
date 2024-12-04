package com.sorbac.adventOfCode.year2024.day;

import com.sorbac.adventOfCode.common.Loc;
import com.sorbac.adventOfCode.year2024.Day2024;

import java.util.ArrayList;
import java.util.List;

public class Day4 extends Day2024 {
    private static final int DAY = Integer.parseInt(Day4.class.getSimpleName().replaceFirst("Day", ""));

    protected Day4() {
        super(DAY);
    }

    protected Day4(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day4().printParts(2468, 1864);
        new Day4("MMMSXXMASM\n" +
                "MSAMXMSMSA\n" +
                "AMXSXMAAMM\n" +
                "MSAMASMSMX\n" +
                "XMASAMXAMM\n" +
                "XXAMMXXAMA\n" +
                "SMSMSASXSS\n" +
                "SAXAMASAAA\n" +
                "MAMMMXMMMM\n" +
                "MXMXAXMASX").printPart1(18);
        new Day4("MMMSXXMASM\n" +
                "MSAMXMSMSA\n" +
                "AMXSXMAAMM\n" +
                "MSAMASMSMX\n" +
                "XMASAMXAMM\n" +
                "XXAMMXXAMA\n" +
                "SMSMSASXSS\n" +
                "SAXAMASAAA\n" +
                "MAMMMXMMMM\n" +
                "MXMXAXMASX").printPart2(9);
    }

    @Override
    protected Object part1() {
        List<String> input = dayStreamLines().toList();
        List<Loc> possibleXmas = findAllChars(input, 'X');
        return possibleXmas.stream().mapToInt(loc -> countXmas(input, loc)).sum();
    }

    private int countXmas(List<String> input, Loc loc) {
        int count = 0;
        if (
                isCharAt(input, loc.left(1), 'M') &&
                        isCharAt(input, loc.left(2), 'A') &&
                        isCharAt(input, loc.left(3), 'S')
        ) {
            count++;
        }
        if (
                isCharAt(input, loc.right(1), 'M') &&
                        isCharAt(input, loc.right(2), 'A') &&
                        isCharAt(input, loc.right(3), 'S')
        ) {
            count++;
        }
        if (
                isCharAt(input, loc.up(1), 'M') &&
                        isCharAt(input, loc.up(2), 'A') &&
                        isCharAt(input, loc.up(3), 'S')
        ) {
            count++;
        }
        if (
                isCharAt(input, loc.down(1), 'M') &&
                        isCharAt(input, loc.down(2), 'A') &&
                        isCharAt(input, loc.down(3), 'S')
        ) {
            count++;
        }
        if (
                isCharAt(input, loc.down(1).left(1), 'M') &&
                        isCharAt(input, loc.down(2).left(2), 'A') &&
                        isCharAt(input, loc.down(3).left(3), 'S')
        ) {
            count++;
        }
        if (
                isCharAt(input, loc.down(1).right(1), 'M') &&
                        isCharAt(input, loc.down(2).right(2), 'A') &&
                        isCharAt(input, loc.down(3).right(3), 'S')
        ) {
            count++;
        }
        if (
                isCharAt(input, loc.up(1).left(1), 'M') &&
                        isCharAt(input, loc.up(2).left(2), 'A') &&
                        isCharAt(input, loc.up(3).left(3), 'S')
        ) {
            count++;
        }
        if (
                isCharAt(input, loc.up(1).right(1), 'M') &&
                        isCharAt(input, loc.up(2).right(2), 'A') &&
                        isCharAt(input, loc.up(3).right(3), 'S')
        ) {
            count++;
        }
        return count;
    }

    private int countMas(List<String> input, Loc loc) {
        if (isMasLeftRight(input, loc) && isMasRightLeft(input, loc)) {
            return 1;
        }
        return 0;
    }

    @Override
    protected Object part2() {
        List<String> input = dayStreamLines().toList();
        List<Loc> possibleXmas = findAllChars(input, 'A');
        return possibleXmas.stream().mapToInt(loc -> countMas(input, loc)).sum();
    }

    private boolean isMasRightLeft(List<String> input, Loc loc) {
        return (isCharAt(input, loc.up(1).right(1), 'M') &&
                isCharAt(input, loc.down(1).left(1), 'S')) ||
                (isCharAt(input, loc.up(1).right(1), 'S') &&
                        isCharAt(input, loc.down(1).left(1), 'M'));
    }

    private boolean isMasLeftRight(List<String> input, Loc loc) {
        return (isCharAt(input, loc.down(1).right(1), 'M') &&
                isCharAt(input, loc.up(1).left(1), 'S')) ||
                (isCharAt(input, loc.down(1).right(1), 'S') &&
                        isCharAt(input, loc.up(1).left(1), 'M'));
    }

    private List<Loc> findAllChars(List<String> input, char x) {
        List<Loc> allX = new ArrayList<>();
        for (int col = 0; col < input.size(); col++) {
            for (int row = 0; row < input.getFirst().length(); row++) {
                if (isCharAt(input, col, row, x)) {
                    allX.add(new Loc(col, row));
                }
            }
        }
        return allX;
    }

    private static boolean isCharAt(List<String> input, Loc loc, char x) {
        return isCharAt(input, (int) loc.col(), (int) loc.row(), x);
    }

    private static boolean isCharAt(List<String> input, int col, int row, char x) {
        if (row < 0 || row >= input.size()) {
            return false;
        }
        if (col < 0 || col >= input.getFirst().length()) {
            return false;
        }
        return input.get(col).charAt(row) == x;
    }
}

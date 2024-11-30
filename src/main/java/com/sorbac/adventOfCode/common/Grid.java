package com.sorbac.adventOfCode.common;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Grid {
    private final char[][] grid;

    public Grid(char[][] grid) {
        this.grid = grid;
    }

    public Grid(int width, int height, char c) {
        this.grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = c;
            }
        }
    }

    public Grid(String grid) {
        this.grid = Arrays.stream(grid.split("\n")).map(String::toCharArray).toArray(char[][]::new);
    }

    public Grid copy() {
        char[][] result = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            result[i] = grid[i].clone(); // Cloning each inner array
        }

        return new Grid(result);
    }

    public int width() {
        return grid[0].length;
    }

    public int height() {
        return grid.length;
    }

    public void apply(Function<Loc, Character> mapping) {
        apply(new Loc(0, 0), new Loc(grid[0].length - 1, grid.length - 1), mapping);
    }

    public void apply(Loc start, Loc end, Function<Loc, Character> mapping) {
        for (int row = (int) start.row(); row <= end.row(); row++) {
            for (int col = (int) start.col(); col <= end.col(); col++) {
                grid[col][row] = mapping.apply(new Loc(col, row));
            }
        }
    }

    public char get(Loc loc) {
        return grid[(int) loc.row()][(int) loc.col()];
    }

    public boolean isOutOfBounds(Loc loc) {
        return loc.row() < 0 || loc.row() >= grid.length || loc.col() < 0 || loc.col() >= grid[0].length;
    }

    public boolean contains(Loc loc) {
        return loc.row() >= 0 && loc.row() < grid.length && loc.col() >= 0 && loc.col() < grid[0].length;
    }

    public long count(char... chars) {
        String stringOfChars = new String(chars);
        return getCharStream()
                .filter(c -> stringOfChars.chars().anyMatch(i -> i == c)).count();
    }

    public IntStream getCharStream() {
        return Arrays.stream(grid).flatMapToInt(row -> new String(row).chars());
    }
}

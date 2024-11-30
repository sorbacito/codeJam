package com.sorbac.adventOfCode.common;

public record Loc(long x, long y) {
    public static Loc parse(String location) {
        String[] split = location.split(",");
        return new Loc(Long.parseLong(split[0]), Long.parseLong(split[1]));
    }

    public long row() {
        return y;
    }

    public long col() {
        return x;
    }

    public Loc move(Direction direction, int steps) {
        return switch (direction) {
            case UP -> up(steps);
            case DOWN -> down(steps);
            case LEFT -> left(steps);
            case RIGHT -> right(steps);
        };

    }

    public Loc move(Direction direction) {
        return switch (direction) {
            case UP -> up();
            case DOWN -> down();
            case LEFT -> left();
            case RIGHT -> right();
        };
    }

    public Loc right() {
        return right(1);
    }

    public Loc right(int steps) {
        return new Loc(x + steps, y);
    }

    public Loc left() {
        return left(1);
    }

    public Loc left(int steps) {
        return new Loc(x - steps, y);
    }

    public Loc down() {
        return down(1);
    }

    public Loc down(int steps) {
        return new Loc(x, y + steps);
    }

    public Loc up() {
        return up(1);
    }

    public Loc up(int steps) {
        return new Loc(x, y - steps);
    }

    public int manhattanDistance(Loc other) {
        return (int) (Math.abs(x - other.x) + Math.abs(y - other.y));
    }
}

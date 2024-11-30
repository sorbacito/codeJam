package com.sorbac.adventOfCode.common;

public enum Direction {
    UP(0), RIGHT(1), DOWN(2), LEFT(3);

    private int value = 0;

    Direction(int value) {
        this.value = value;
    }

    public Direction turnBack() {
        return ofValue((value + 2) % 4);
    }

    public Direction turnLeft() {
        return ofValue((value + 3) % 4);
    }

    public Direction turnRight() {
        return ofValue((value + 1) % 4);
    }

    private Direction ofValue(int value) {
        return switch (value) {
            case 0 -> UP;
            case 1 -> RIGHT;
            case 2 -> DOWN;
            case 3 -> LEFT;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }
}

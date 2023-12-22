package com.sorbac.adventOfCode.common;

public record LocInt(int x, int y) {
    public int row() {
        return y;
    }

    public int col() {
        return x;
    }
}

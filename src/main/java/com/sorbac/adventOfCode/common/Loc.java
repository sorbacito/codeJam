package com.sorbac.adventOfCode.common;

public record Loc(long x, long y) {
    public long row() {
        return y;
    }

    public long col() {
        return x;
    }
}

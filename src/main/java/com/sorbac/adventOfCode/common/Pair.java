package com.sorbac.adventOfCode.common;

public record Pair<A, B>(A left, B right) {
    public A row() {
        return left;
    }

    public B col() {
        return right;
    }

    public B x() {
        return col();
    }

    public A y() {
        return row();
    }
}

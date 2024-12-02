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

    public A getFirst() {
        return row();
    }

    public B getSecond() {
        return col();
    }

    public static <A, B> Pair<A, B> of(A left, B right) {
        return new Pair<>(left, right);
    }
}

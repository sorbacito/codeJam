package com.sorbac.adventOfCode.common;

import lombok.Data;

@Data
public class Tuple<A, B, C> {
    private final A a;
    private final B b;
    private final C c;

    public static <A, B, C> Tuple<A, B, C> of(A a, B b, C c) {
        return new Tuple<>(a, b, c);
    }

    public A a() {
        return getA();
    }

    public B b() {
        return getB();
    }

    public C c() {
        return getC();
    }
}

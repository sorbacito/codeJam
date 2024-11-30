package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.common.Tuple;
import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Day2 extends Day2015 {
    private static final int DAY_NR = 2;

    protected Day2() {
        super(DAY_NR);
    }

    protected Day2(String input) {
        super(DAY_NR, input);
    }

    public static void main(String[] args) {
        new Day2("2x3x4").printParts(58L, 34L);
        new Day2("1x1x10").printParts(43L, 14L);

        new Day2().printParts(1606483L, 3842356);
    }

    @Override
    protected Object part1() {
        Function<Present, Long> getWrappingPaper = Present::getWrappingPaper;
        return sumByFunction(getWrappingPaper);
    }

    private long sumByFunction(Function<Present, Long> getWrappingPaper) {
        return dayStreamLongs("x")
                .map(p -> new Present(p.get(0), p.get(1), p.get(2)))
                .map(getWrappingPaper)
                .mapToLong(Long::longValue)
                .sum();
    }

    @Override
    protected Object part2() {
        Function<Present, Long> getWrappingPaper = Present::getRibbonLength;
        return sumByFunction(getWrappingPaper);

    }

    private static class Present extends Tuple<Long, Long, Long> {

        public Present(Long aLong, Long aLong2, Long aLong3) {
            super(aLong, aLong2, aLong3);
        }

        public long getWrappingPaper() {
            long side1 = a() * b();
            long side2 = b() * c();
            long side3 = c() * a();
            return 2 * (side1 + side2 + side3) + Math.min(Math.min(side1, side2), side3);
        }

        public long getRibbonLength() {
            ArrayList<Long> sides = new ArrayList<>(List.of(a(), b(), c()));
            sides.sort(Long::compareTo);

            return 2 * (sides.get(0) + sides.get(1)) + a() * b() * c();
        }

    }
}

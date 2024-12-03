package com.sorbac.adventOfCode.year2019.day;


import com.sorbac.adventOfCode.year2019.Day2019;

import java.util.ArrayList;
import java.util.List;

public class Day2 extends Day2019 {
    private static final int DAY = Integer.parseInt(Day2.class.getSimpleName().replaceFirst("Day", ""));
    private final boolean changeInput;

    protected Day2() {
        super(DAY);
        changeInput = true;
    }

    protected Day2(String input) {
        super(DAY, input);
        changeInput = false;
    }

    public static void main(String[] args) {
        new Day2().printParts();
        new Day2("1,9,10,3,2,3,11,0,99,30,40,50").printPart1(3500L);
        new Day2("2,3,0,3,99").printPart1(2L);
//        new Day2("test input 2").printPart1("expected result 2");
    }

    @Override
    protected Object part1() {
        return computeOutput(12L, 2L);
    }

    @Override
    protected Object part2() {
        for (long noun = 0; noun < 100; noun++) {
            for (long verb = 0; verb < 100; verb++) {
                if (computeOutput(noun, verb) == 19690720) {
                    return 100 * noun + verb;
                }
            }
        }
        return -1;
    }

    private Long computeOutput(long noun, long verb) {
        List<Long> input = new ArrayList<>(dayStreamLongs(",").findFirst().orElseGet(ArrayList::new));
        if (changeInput) {
            changeInput(input, noun, verb);
        }
        int i = 0;
        while (input.get(i) != 99) {
            if (input.get(i) == 1) {
                input.set(input.get(i + 3).intValue(), input.get(input.get(i + 1).intValue()) + input.get(input.get(i + 2).intValue()));
                i += 4;
            } else if (input.get(i) == 2) {
                input.set(input.get(i + 3).intValue(), input.get(input.get(i + 1).intValue()) * input.get(input.get(i + 2).intValue()));
                i += 4;
            }
        }
        return input.getFirst();
    }

    private void changeInput(List<Long> input, long noun, long verb) {
        input.set(1, noun);
        input.set(2, verb);
    }
}

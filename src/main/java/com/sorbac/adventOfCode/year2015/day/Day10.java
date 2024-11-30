package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.common.Day;
import com.sorbac.adventOfCode.year2015.Day2015;

public class Day10 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day10.class.getSimpleName().replaceFirst("Day", ""));

    protected Day10() {
        super(DAY);
    }

    protected Day10(String input) {
        super(DAY, input);
    }

    private int turns = 1;
    private int turns2 = 0;

    public static void main(String[] args) {
        new Day10("1").printParts(2);
        new Day10("11").printParts(2);
        new Day10("21").printParts(4);
        new Day10("1211").printParts(6);
        new Day10("111221").printParts(6);
        new Day10("3113322113").turns(40, 50).printParts(329356, 4666278);
    }

    private Day turns(int turns) {
        this.turns = turns;
        return this;
    }

    private Day turns(int turns, int turns2) {
        this.turns = turns;
        this.turns2 = turns2;
        return this;
    }

    @Override
    protected Object part1() {
        String number = day();
        for (int i = 0; i < turns; i++) {
            number = lookAndSay(number);
        }
        return number.length();
    }

    private String lookAndSay(String number) {
        String cur = number;
        StringBuilder res = new StringBuilder();
        char lastDigit = ' ';
        int lastDigitPos = 0;
        for (int i = 0; i < number.length(); i++) {
            char digit = cur.charAt(i);
            if (i == 0) {
                lastDigit = digit;
            } else if (digit != lastDigit) {
                int occurences = i - lastDigitPos;
                res.append(occurences).append(lastDigit);
                lastDigit = digit;
                lastDigitPos = i;
            }
        }

        res.append(number.length() - lastDigitPos).append(lastDigit);
        return res.toString();
    }

    @Override
    protected Object part2() {
        String number = day();
        for (int i = 0; i < turns2; i++) {
            number = lookAndSay(number);
        }
        return turns2 > 0 ? number.length() : null;
    }
}

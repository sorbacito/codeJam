package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Day11 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day11.class.getSimpleName().replaceFirst("Day", ""));

    protected Day11() {
        super(DAY);
    }

    protected Day11(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        System.out.println("hijklmmn" + (Stream.of(increasingStraight, onlyValidLetters.negate())
                .allMatch(p -> p.test("hijklmmn".toCharArray())) ? " OK" : " NOT OK"));
        System.out.println("abbceffg" + (Stream.of(increasingStraight.negate(), onlyValidLetters, twoPairOfLetters)
                .allMatch(p -> p.test("abbceffg".toCharArray())) ? " OK" : " NOT OK"));
        System.out.println("abbcegjk" + (Stream.of(twoPairOfLetters.negate())
                .allMatch(p -> p.test("abbcegjk".toCharArray())) ? " OK" : " NOT OK"));
        new Day11("abcdefgh").printPart1("abcdffaa");
        new Day11("ghijklmn").printPart1("ghjaabcc");
        new Day11("vzbxkghb").printParts("vzbxxyzz", "vzcaabcc");
    }

    @Override
    protected Object part1() {
        char[] password = day().toCharArray();
        return getNextPassword(password);
    }

    private static String getNextPassword(char[] password) {
        do {
            increment(password);
        } while (!isValidPassword.test(password));
        return new String(password);
    }

    @Override
    protected Object part2() {
        return getNextPassword(part1().toString().toCharArray());
    }

    private static void increment(char[] chars) {
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == 'z') {
                chars[i] = 'a';
            } else {
                chars[i]++;
                break;
            }
        }
    }

    private static final Predicate<char[]> increasingStraight = (chars) -> {
        for (int i = 0; i < chars.length - 2; i++) {
            if (chars[i] == chars[i + 1] - 1 && chars[i + 1] == chars[i + 2] - 1) {
                return true;
            }
        }
        return false;
    };

    private static final Predicate<char[]> onlyValidLetters = (chars) -> {
        for (char c : chars) {
            if (c == 'i' || c == 'o' || c == 'l') {
                return false;
            }
        }
        return true;
    };

    private static final Predicate<char[]> twoPairOfLetters = (chars) -> {
        int pairs = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                pairs++;
                i++;
                if (pairs > 1) return true;
            }
        }
        return false;
    };

    private static final Predicate<char[]> isValidPassword = (chars) ->
            Stream.of(increasingStraight, onlyValidLetters, twoPairOfLetters).allMatch(p -> p.test(chars));
}

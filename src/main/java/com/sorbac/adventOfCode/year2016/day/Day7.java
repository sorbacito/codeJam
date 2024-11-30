package com.sorbac.adventOfCode.year2016.day;


import com.sorbac.adventOfCode.common.Pair;
import com.sorbac.adventOfCode.year2016.Day2016;

import java.util.ArrayList;
import java.util.List;

public class Day7 extends Day2016 {
    private static final int DAY = Integer.parseInt(Day7.class.getSimpleName().replaceFirst("Day", ""));

    protected Day7() {
        super(DAY);
    }

    protected Day7(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day7("abba[mnop]qrst").printPart1(1);
        new Day7("abcd[bddb]xyyx").printPart1(0);
        new Day7("aaaa[qwer]tyui").printPart1(0);
        new Day7("ioxxoj[asdfgh]zxcvbn").printPart1(1);
        new Day7("aba[bab]xyz").printPart2(1);
        new Day7("xyx[xyx]xyx").printPart2(0);
        new Day7("aaa[kek]eke").printPart2(1);

        new Day7().printParts(110);
    }

    @Override
    protected Object part1() {
        return (int) dayStream()
                .map(Day7::splitToInsideOutside)
                .filter(Day7::supportsTLS).count();
    }

    @Override
    protected Object part2() {
        return (int) dayStream()
                .map(Day7::splitToInsideOutside)
                .filter(Day7::supportsSSL).count();
    }

    private static Pair<List<String>, List<String>> splitToInsideOutside(String line) {
        String[] splitOne = line.split("\\[");
        List<String> myOutside = new ArrayList<>();
        myOutside.add(splitOne[0]);
        List<String> myInside = new ArrayList<>();
        for (int i = 1; i < splitOne.length; i++) {
            String[] splitTwo = splitOne[i].split("]");
            myInside.add(splitTwo[0]);
            myOutside.add(splitTwo[1]);
        }
        return new Pair<>(myOutside, myInside);
    }

    private static boolean supportsSSL(Pair<List<String>, List<String>> pair) {
        return pair.left().stream().map(Day7::getAbas).flatMap(List::stream).anyMatch(aba ->
                pair.right().stream().anyMatch(bab -> bab.contains("" + aba.charAt(1) + aba.charAt(0) + aba.charAt(1))));
    }

    private static boolean supportsTLS(Pair<List<String>, List<String>> line) {
        return line.left().stream().anyMatch(Day7::isAbba)
                && line.right().stream().noneMatch(Day7::isAbba);
    }

    private static List<String> getAbas(String s) {
        List<String> myAbas = new ArrayList<>();
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 2)
                    && s.charAt(i) != s.charAt(i + 1))
                myAbas.add(s.substring(i, i + 3));
        }
        return myAbas;
    }

    private static boolean isAbba(String s) {
        for (int i = 0; i < s.length() - 3; i++) {
            if (s.charAt(i) == s.charAt(i + 3)
                    && s.charAt(i + 1) == s.charAt(i + 2)
                    && s.charAt(i) != s.charAt(i + 1))
                return true;
        }
        return false;
    }
}

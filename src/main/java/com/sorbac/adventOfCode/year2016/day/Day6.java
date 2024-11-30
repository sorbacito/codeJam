package com.sorbac.adventOfCode.year2016.day;


import com.sorbac.adventOfCode.year2016.Day2016;

public class Day6 extends Day2016 {
    private static final int DAY = Integer.parseInt(Day6.class.getSimpleName().replaceFirst("Day", ""));

    protected Day6() {
        super(DAY);
    }

    protected Day6(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day6().setExample(1).printParts("easter", "advent");

        new Day6().printParts("qoclwvah", "ryrgviuv");
    }

    @Override
    protected Object part1() {
        String[] lines = day().split(DEFAULT_DELIMITER);
        int[][] charCounts = new int[lines[0].length()][26];
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                charCounts[i][line.charAt(i) - 'a']++;
            }
        }
        StringBuilder myResult = new StringBuilder();
        for (int[] charCount : charCounts) {
            int max = 0;
            int maxIndex = 0;
            for (int i = 0; i < charCount.length; i++) {
                if (charCount[i] > max) {
                    max = charCount[i];
                    maxIndex = i;
                }
            }
            myResult.append((char) ('a' + maxIndex));
        }
        return myResult.toString();
    }

    @Override
    protected Object part2() {
        String[] lines = day().split(DEFAULT_DELIMITER);
        int[][] charCounts = new int[lines[0].length()][26];
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                charCounts[i][line.charAt(i) - 'a']++;
            }
        }
        StringBuilder myResult = new StringBuilder();
        for (int[] charCount : charCounts) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < charCount.length; i++) {
                if (charCount[i] > 0 && charCount[i] < min) {
                    min = charCount[i];
                    minIndex = i;
                }
            }
            myResult.append((char) ('a' + minIndex));
        }
        return myResult.toString();
    }
}

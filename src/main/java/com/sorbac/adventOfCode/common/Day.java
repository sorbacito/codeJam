package com.sorbac.adventOfCode.common;

import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public abstract class Day {
    public static final String DEFAULT_DELIMITER = "\n";
    private final int year;
    private final int day;
    private final DayPaths dayPaths;
    private int example = 0;
    @Getter
    private Object argument;

    private final Map<String, Long> tests;

    private String input;

    protected Day(int year, int day) {
        this(year, day, null);
    }

    protected Day(int year, int day, String input) {
        this.year = year;
        this.day = day;
        dayPaths = new DayPaths(year, day);
        tests = new HashMap<>();
        this.input = input;
    }

    public static List<String> readLines(Scanner in1) {
        List<String> lines1 = new ArrayList<>();
        while (in1.hasNextLine()) {
            lines1.add(in1.nextLine());
        }
        return lines1;
    }

    public void printParts() {
        printParts(null, null);
    }

    public void printParts(Object expectedPart1) {
        printParts(expectedPart1, null);
    }

    public void printParts(Object expectedPart1, Object expectedPart2) {
        printPart1(expectedPart1);
        printPart2(expectedPart2);
    }

    public void printPart1(Object expectedPart1) {
        Object part1 = part1();
        if (part1 != null) System.out.println("Part 1: " + part1 + isExpected(part1, expectedPart1));
    }

    public void printPart2(Object expectedPart2) {
        Object part2 = part2();
        if (part2 != null) System.out.println("Part 2: " + part2 + isExpected(part2, expectedPart2));
    }

    private String isExpected(Object answer, Object expectedAnswer) {
        if (expectedAnswer == null) return "";
        return answer != null && answer.equals(expectedAnswer) ? " OK" : " NOT OK";
    }

    public static String getResourceAsString(String resource) {
        try {
            return Files.readString(getResource(resource).toPath());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static File getResource(String path) {
        return new File("src/main/resources/com/sorbac/adventOfCode/" + path);
    }

    protected Day setExample(int example) {
        this.example = example;
        input = getResourceAsString(dayPaths.getDayExamplePath(example));
        return this;
    }

    public Day setArgument(Object argument) {
        this.argument = argument;
        return this;
    }

    protected String day() {
        if (input == null) {
            input = getResourceAsString(dayPaths.getDayPath());
        }
        return input;
    }

    protected Stream<String> dayStream() {
        return dayStream(DEFAULT_DELIMITER);
    }

    protected Stream<List<Long>> dayStreamLongs() {
        return dayStreamLongs(DEFAULT_DELIMITER);
    }

    protected Stream<List<Long>> dayStreamLongs(String delimiter) {
        return dayStream().map(line -> Arrays.stream(line.split(delimiter)).map(Long::parseLong).toList());
    }

    protected Stream<String> dayStream(String delimiter) {
        return Stream.of(day().split(delimiter));
    }

    protected abstract Object part1();

    protected abstract Object part2();

    protected void addTest(String input, long expectedOutput) {
        tests.put(input, expectedOutput);
    }

    private record DayPaths(int year, int day) {
        public String getDayPath() {
            return getYearDay() + ".txt";
        }

        public String getDayExamplePath(int example) {
            return getYearDay() + "-" + example + ".txt";
        }

        private String getYearDay() {
            return "year" + year + "/day" + day;
        }
    }
}

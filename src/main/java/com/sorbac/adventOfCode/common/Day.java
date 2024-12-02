package com.sorbac.adventOfCode.common;

import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public abstract class Day {
    public static final String DEFAULT_DELIMITER = "\n";
    public static final String ANY_BLANK_CHARACTERS_REGEX = "\\s+";
    private final int year;
    private final int day;
    private final DayPaths dayPaths;
    private int example = 0;
    @Getter
    private Object argument;

    private final Map<String, Long> tests;

    private String input;

    protected Day(int year, int day) {
        this(year, day, "");
    }

    protected Day(int year, int day, String input) {
        this.year = year;
        this.day = day;
        dayPaths = new DayPaths(year, day);
        tests = new HashMap<>();
        this.input = input;
    }

    protected Day(int year, int day, String... lines) {
        this(year, day, String.join(DEFAULT_DELIMITER, lines));
    }

    protected List<String> readLines() {
        return Arrays.stream(day().split("\n")).toList();
    }

    public static List<String> readLines(Scanner in1) {
        List<String> lines1 = new ArrayList<>();
        while (in1.hasNextLine()) {
            lines1.add(in1.nextLine());
        }
        return lines1;
    }
}

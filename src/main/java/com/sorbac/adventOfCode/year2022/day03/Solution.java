package com.sorbac.adventOfCode.year2022.day03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day03/input.txt";
    private final String line;
    private final List<String> threeLines;

    public Solution(String line) {
        this.line = line;
        this.threeLines = null;
    }

    public Solution(List<String> threeLines) {
        this.line = null;
        this.threeLines = threeLines;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
        int sum = 0;
        int sumPartTwo = 0;
        List<String> threeLines = new ArrayList<>(3);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            sum += new Solution(line).getPriorityOfArrangedItem();
            if (threeLines.size() == 2) {
                threeLines.add(line);
                sumPartTwo += new Solution(threeLines).getPriorityOfSharedItem();
                threeLines.clear();
            } else {
                threeLines.add(line);
            }

        }

        System.out.println("Sum: " + sum);
        System.out.println("Sum part two: " + sumPartTwo);
    }

    public int getPriorityOfSharedItem() {
        char item = getCommonItem();
        return charPriority(item);
    }

    private char getCommonItem() {
        assert threeLines != null;
        for (char c : threeLines.get(0).toCharArray()) {
            if (threeLines.get(1).indexOf(c) >= 0 && threeLines.get(2).indexOf(c) >= 0) {
                return c;
            }
        }
        return 0;
    }

    public int getPriorityOfArrangedItem() {
        char item = itemInBothCompartments();
        return charPriority(item);
    }

    private int charPriority(char item) {
        if ('a' <= item && 'z' >= item) {
            return item - 'a' + 1;
        } else {
            return item - 'A' + 27;
        }
    }

    private char itemInBothCompartments() {
        assert line != null;
        Set<Character> firstCompartment = new HashSet<>(line.length()/2);
        for (int i = 0; i < line.length(); i++) {
            if (i < line.length()/2) {
                firstCompartment.add(line.charAt(i));
            } else {
                if (firstCompartment.contains(line.charAt(i))) {
                    return line.charAt(i);
                }
            }
        }
        return '?';
    }
}

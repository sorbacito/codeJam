package com.sorbac.adventOfCode.year2022.day06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day06/input.txt";
    public static final int MARKER_SIZE = 4;
    public static final int MESSAGE_SIZE = 14;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
        String line = in.nextLine();
        System.out.println("Marker position: " + new Solution().positionOfMarker(line));
        System.out.println("Message position: " + new Solution().positionOfMessage(line));
    }

    public int positionOfMarker(String s) {
        for (int i = MARKER_SIZE - 1; i < s.length(); i++) {
            if (islastXCharsDifferent(s, i, MARKER_SIZE)) {
                return i + 1;
            }
        }
        return -1;
    }

    public int positionOfMessage(String s) {
        for (int i = MESSAGE_SIZE - 1; i < s.length(); i++) {
            if (islastXCharsDifferent(s, i, MESSAGE_SIZE)) {
                return i + 1;
            }
        }
        return -1;
    }

    private boolean islastXCharsDifferent(String line, int idx, int numberOfDifferentChars) {
        Set<Character> set = new HashSet<>(numberOfDifferentChars);
        for (int i = 0; i < numberOfDifferentChars; i++) {
            char c = line.charAt(idx - i);
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }
}

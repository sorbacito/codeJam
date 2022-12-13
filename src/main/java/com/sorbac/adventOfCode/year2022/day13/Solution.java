package com.sorbac.adventOfCode.year2022.day13;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day12/input.txt";
    public static final String TEST_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day12/test.txt";

    private final String line1;
    private final String line2;

    public Solution(String line1, String line2) {
        this.line1 = line1;
        this.line2 = line2;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE))));
        int sum = 0;
        int i = 1;
        while (in.hasNextLine()) {
            String line1 = in.nextLine();
            String line2 = in.nextLine();
            if (in.hasNextLine()) in.nextLine();
            if (new Solution(line1, line2).isSmaller()) sum += i++;
        }
        System.out.println(sum);
    }

    public boolean isSmaller() {
        Integer i1 = 0;
        Integer i2 = 0;
        int compRes = 0;
        while (i1 < line1.length() && i2 < line2.length()) {
            char c1 = line1.charAt(i1);
            char c2 = line2.charAt(i2);
            if (c1 == '[' && c2 == '[') {
                compRes = compareLists(i1, i2);
                if (compRes != 0) break;
            } else if (c1 != '[' && c2 != '[') {
                compRes = compareInts(i1, i2);
                if (compRes != 0) break;
            } else {
                compRes = compareDifferent(i1, i2);
                if (compRes != 0) break;
            }
        }
        return compRes < 0;
    }

    private int compareInts(Integer i1, Integer i2) {
        int val1 = readInt(line1, i1);
        int val2 = readInt(line2, i2);
        return Integer.compare(val1, val2);
    }

    private int readInt(String s, Integer i) {
        int firstComma = s.substring(i).indexOf(',');
        int firstStartList = s.substring(i).indexOf('[');
        int firstEndList = s.substring(i).indexOf(']');
        int firstList = firstStartList > 0 ? firstStartList : firstEndList;
        int delIdx = Math.min(firstComma, firstList);
        int val = Integer.parseInt(line1.substring(i, i + delIdx));
        i += delIdx;
        return val;
    }

    private int compareLists(Integer i1, Integer i2) {
        i1++; i2++;

        return 0;
    }

    private int compareDifferent(Integer i1, Integer i2) {
        return 0;
    }

}

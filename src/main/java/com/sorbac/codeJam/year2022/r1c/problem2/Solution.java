package com.sorbac.codeJam.year2022.r1c.problem2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            Case myInput = readCase(in);
            System.out.println("Case #" + i + ": " + answer(myInput));
        }
    }

    private static String answer(Case input) {
        return null;
    }

    private static Case readCase(Scanner in) {
        return null;
    }

    private static int[] readInts(Scanner aIn) {
        String[] myInput = aIn.nextLine().split(" ");
        int[] myBffs = new int[myInput.length];
        for (int i = 0; i < myInput.length; i++) {
            myBffs[i] = Integer.parseInt(myInput[i]);
        }
        return myBffs;
    }

    private static class Case {

    }
}

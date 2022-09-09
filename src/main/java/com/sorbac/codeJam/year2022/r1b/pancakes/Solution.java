package com.sorbac.codeJam.year2022.r1b.pancakes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int[] myInts = readInts(in);
            System.out.println("Case #" + i + ": " + paidPancakes(myInts));
        }
    }

    private static int paidPancakes(int[] pancakes) {
        int min = 0;
        int right = 0;
        int left = pancakes.length - 1;
        int paid = 0;
        while (right <= left) {
            int cMin;
            if (pancakes[right] < pancakes[left]) {
                cMin = pancakes[right++];
            } else {
                cMin = pancakes[left--];
            }
            if (min <= cMin) {
                paid++;
                min = cMin;
            }
        }
        return paid;
    }

    private static int[] readInts(Scanner aIn) {
        int[] myBffs = new int[Integer.parseInt(aIn.nextLine())];
        String[] myInput = aIn.nextLine().split(" ");
        for (int i = 0; i < myInput.length; i++) {
            myBffs[i] = Integer.parseInt(myInput[i]);
        }
        return myBffs;
    }

}

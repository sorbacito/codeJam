package com.sorbac.codeJam.year2022.r1b.inflations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int[][] myCase = readCase(in);
            System.out.println("Case #" + i + ": " + countInflations(myCase));
        }
    }

    private static long countInflations(int[][] myCase) {
        Arrays.sort(myCase[0]);
        long result = myCase[0][0];
        int minC = myCase[0][0];
        int maxC = myCase[0][myCase[0].length  - 1];
        for (int[] customer : myCase) {
            Arrays.sort(customer);
            
        }
        return result;
    }

    private static int[][] readCase(Scanner in) {
        int[] conf = readInts(in);
        int[][] myCase = new int[conf[0]][conf[1]];
        for (int i = 0; i < conf[0]; i++) {
            myCase[i] = readInts(in);
        }
        return myCase;
    }

    private static int[] readInts(Scanner aIn) {
        String[] myInput = aIn.nextLine().split(" ");
        int[] myBffs = new int[myInput.length];
        for (int i = 0; i < myInput.length; i++) {
            myBffs[i] = Integer.parseInt(myInput[i]);
        }
        return myBffs;
    }

}

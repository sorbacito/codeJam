package com.sorbac.codeJam._2021.qualification.reverseSort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int[] myInts = readArray(in);
            System.out.println("Case #" + i + ": " + myAnswer(myInts));
        }
    }

    public static long myAnswer(int[] myInts) {
        long sum = 0;

        for (int v = 0; v < myInts.length - 1; v++) {
            int minIdx = findMin(myInts, v + 1);
            int diff = minIdx - v;
            sum += diff + 1;
            for (int i = 0; i < (diff + 1)/2; i++) {
                int left = myInts[v + i];
                int right = myInts[v + diff - i];
                myInts[v + i] = right;
                myInts[v + diff - i] = left;
            }
        }
        return sum;
    }

    private static int findMin(int[] myInts, int value) {
        for (int i = 0; i < myInts.length; i++) {
            if (myInts[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static int[] readArray(Scanner aIn) {
        int[] myBffs = new int[Integer.parseInt(aIn.nextLine())];
        String[] myInput = aIn.nextLine().split(" ");
        for (int i = 0; i < myInput.length; i++) {
            myBffs[i] = Integer.parseInt(myInput[i]);
        }
        return myBffs;
    }

}

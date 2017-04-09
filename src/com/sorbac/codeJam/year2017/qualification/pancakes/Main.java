package com.sorbac.codeJam.year2017.qualification.pancakes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static final int SMILE_FACE = 1;
    public static final int BLANK_FACE = 0;
    public static final int IMPOSSIBLE = -1;
    private final int[] thePancakes;
    private int theFlipperSize;

    public Main(int[] aPancakes, int aFlipperSize) {
        thePancakes = aPancakes;
        theFlipperSize = aFlipperSize;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.valueOf(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            Main myCase = readCase(in.nextLine());
            long myCounts = myCase.countFlips();
            String myAnswer = myCounts == IMPOSSIBLE ? "IMPOSSIBLE" : String.valueOf(myCounts);
            System.out.println("Case #" + i + ": " + myAnswer);
        }
    }

    public long countFlips() {
        int myFlips = 0;
        int myLeftLimit = 0;
        while (canFlip(myLeftLimit)) {
            if (thePancakes[myLeftLimit] == SMILE_FACE) {
                while (myLeftLimit < thePancakes.length && thePancakes[myLeftLimit] == SMILE_FACE) {
                    myLeftLimit++;
                }
            }
            if (canFlip(myLeftLimit)) {
                flipPancakes(myLeftLimit);
                myFlips++;
            }
        }
        return myLeftLimit == thePancakes.length ? myFlips : IMPOSSIBLE;
    }

    private void flipPancakes(int myLeftLimit) {
        for (int i = 0; i < theFlipperSize; i++) {
            thePancakes[myLeftLimit + i] = (thePancakes[myLeftLimit + i] + 1) % 2;
        }
    }

    private boolean canFlip(int myLeftLimit) {
        return myLeftLimit + theFlipperSize <= thePancakes.length;
    }

    private static Main readCase(String aLine) {
        String[] myElements = aLine.split(" ");
        int[] myPancakes = new int[myElements[0].length()];
        char[] myInput = myElements[0].toCharArray();
        int i = 0;
        for (; i < myInput.length; i++) {
            if (myInput[i] == '+') {
                myPancakes[i] = 1;
            } else if (myInput[i] != '-') {
                break;
            }
        }
        return new Main(myPancakes, Integer.valueOf(myElements[1]));
    }
}

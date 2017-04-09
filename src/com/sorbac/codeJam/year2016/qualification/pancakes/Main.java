package com.sorbac.codeJam.year2016.qualification.pancakes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    private static final int FORWARD = 1;
    private static final int BACKWARD = -1;
    public static final int SMILE_FACE = 1;
    public static final int BLANK_FACE = 0;
    private final int[] thePancakes;
    private int theLeftLimit;
    private int theRightLimit;

    public Main(int[] aPancakes, int aRightLimit) {
        thePancakes = aPancakes;
        theLeftLimit = 0;
        theRightLimit = aRightLimit;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.valueOf(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            Main myCase = readCase(in.nextLine());
            System.out.println("Case #" + i + ": " + myCase.countFlips());
        }
    }

    public long countFlips() {
        int myFlips = 0;
        int myPlusSign = SMILE_FACE;
        int myDirection = FORWARD;
        while (checkContinue()) {
            if (myDirection == FORWARD) {
                if (thePancakes[theLeftLimit] == myPlusSign) {
                    while (checkContinue() && thePancakes[theLeftLimit] == myPlusSign) {
                        theLeftLimit++;
                    }
                    if (checkContinue()) {
                        myFlips++;
                    }
                }
                if (checkContinue() && thePancakes[theLeftLimit] != myPlusSign) {
                    myFlips++;
                    while (checkContinue() && thePancakes[theLeftLimit] != myPlusSign) {
                        theLeftLimit++;
                    }
                }
                while (checkContinue() && thePancakes[theRightLimit] == myPlusSign) {
                    theRightLimit--;
                }
                myDirection = BACKWARD;
                myPlusSign = BLANK_FACE;
            } else {
                if (thePancakes[theRightLimit] == myPlusSign) {
                    myFlips++;
                    while (checkContinue() && thePancakes[theRightLimit] == myPlusSign) {
                        theRightLimit--;
                    }
                }
                if (checkContinue() && thePancakes[theRightLimit] != myPlusSign) {
                    myFlips++;
                    while (checkContinue() && thePancakes[theRightLimit] != myPlusSign) {
                        theRightLimit--;
                    }
                }
                while (checkContinue() && thePancakes[theLeftLimit] == myPlusSign) {
                    theLeftLimit++;
                }
                myDirection = FORWARD;
                myPlusSign = SMILE_FACE;
            }
        }
        return myFlips;
    }

    private boolean checkContinue() {
        return theLeftLimit <= theRightLimit;
    }

    private static Main readCase(String aLine) {
        int[] myPancakes = new int[100];
        char[] myInput = aLine.toCharArray();
        int i = 0;
        for (; i < myInput.length; i++) {
            if (myInput[i] == '+') {
                myPancakes[i] = 1;
            } else if (myInput[i] != '-') {
                break;
            }
        }
        return new Main(myPancakes, --i);
    }
}

package com.sorbac.codeJam.qualification2016.normal.fractiles;/*
 *  Copyright 1999,2001 Clearstream Services, Luxembourg.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information
 *  of Clearstream Services ("Confidential Information").  You
 *  shall not disclose such Confidential Information and shall use
 *  it only in accordance with the terms of the license agreement
 *  you entered into with CS.
 *
 *  @author: Stanislav Kovalcin (ky234) |HUB team|
 *  Date: 10.04.16
 *  Time: 2:14   
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private int theTiles;
    private int theComplexity;

    public Main(int aTiles, int aComplexity) {
        theTiles = aTiles;
        theComplexity = aComplexity;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int myTiles = in.nextInt();
            int myComplexity = in.nextInt();
            int myTriesNumber = in.nextInt();
            List<Long> myTries = new Main(myTiles, myComplexity).generateTries(myTriesNumber);
            System.out.print("Case #" + i + ":");
            if (myTries == null) {
                System.out.print(" IMPOSSIBLE");
            } else {
                for (Long myCoin : myTries) {
                    System.out.print(" " + myCoin);
                }
            }
            System.out.println();
        }
    }

    private List<Long> generateTries(int aTriesNumber) {
        if (theTiles <= aTriesNumber) {
            return generateSimpleTries(theTiles);
        } else {
            return generateFullTries(aTriesNumber);
        }
    }

    private List<Long> generateFullTries(int aTriesNumber) {
        if (aTriesNumber * theComplexity < theTiles) {
            return null;
        } else {
            long base = 0;
            long current = 0;
            for (int i = 0; i < theComplexity - 1; i++) {
                current += Math.pow(theTiles, i);
            }
            current += theComplexity;

        }
        return null;
    }

    private List<Long> generateSimpleTries(int aTiles) {
        List<Long> myTries = new ArrayList<Long>(aTiles);
        long myShift = (long) Math.pow(theTiles, theComplexity - 1);
        for (int i = 0; i < aTiles; i++) {
            myTries.add(1 + i * myShift);
        }
        return myTries;
    }
}

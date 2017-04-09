package com.sorbac.codeJam.year2016.qualification.countingSheep;/*
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
 *  Date: 09.04.16
 *  Time: 18:55   
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    private final long theSheeps;
    private final int[] theDigits = new int[10];
    private int theLeftDigits = 10;

    public Main(long aSheeps) {
        theSheeps = aSheeps;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            long mySheeps = in.nextLong();
            String mySheep = new Main(mySheeps).countSheeps();
            System.out.println("Case #" + i + ": " + mySheep);
        }
    }

    public String countSheeps() {
        if (theSheeps == 0) {
            return "INSOMNIA";
        }
        long i = 1;
        long myCurrentSheep = theSheeps;
        while (!foundAllDigits(myCurrentSheep)) {
            i++;
            myCurrentSheep = theSheeps * i;
        }
        return String.valueOf(myCurrentSheep);
    }

    private boolean foundAllDigits(long aCurrentSheep) {
        while (aCurrentSheep != 0) {
            int myRest = (int) aCurrentSheep % 10;
            if (theDigits[myRest] == 0) {
                if (--theLeftDigits == 0) {
                    return true;
                }
                theDigits[myRest] = 1;
            }
            aCurrentSheep /= 10;
        }
        return false;
    }
}

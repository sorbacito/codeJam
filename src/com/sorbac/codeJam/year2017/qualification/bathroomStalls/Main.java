package com.sorbac.codeJam.year2017.qualification.bathroomStalls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static final int MAX_IDX = 0;
    public static final int MIN_IDX = 1;

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.valueOf(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            long[] myInts = readInts(in);
            long[] myAnswer = getLastPersonMaxMinCalculated(myInts[0], myInts[1]);
            System.out.println("Case #" + i + ": " + myAnswer[MAX_IDX] + " " + myAnswer[MIN_IDX]);
        }
    }

    public static long[] getLastPersonMaxMinCalculated(long mySpace, long myNoOfPeople) {
        int layerNumber = 1;
        long myLayerPow = 1;
        while (layerNumber < 63 && 2 * myLayerPow - 1 < myNoOfPeople) {
            myLayerPow *= 2;
            layerNumber++;
        }
        long mySmallerSpace = (mySpace - myLayerPow + 1) / myLayerPow;
        long myBiggerSpaceNumber = mySpace - myLayerPow + 1 - myLayerPow*mySmallerSpace;
        if (myNoOfPeople <= myLayerPow - 1 + myBiggerSpaceNumber) {
            return divideSpace(mySmallerSpace + 1);
        } else {
            return divideSpace(mySmallerSpace);
        }
    }

    public static long[] getLastPersonMaxMinPriorityQueue(long mySpace, long myNoOfPeople) {
        PriorityQueue<Long> myFreeSpace = new PriorityQueue<Long>(10000000, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o2.compareTo(o1);
            }
        });
        myFreeSpace.add(mySpace);
        for (long i = 0; i < myNoOfPeople - 1; i++) {
            if (i %10000000 == 0) System.out.println(i);
            long[] myMaxMin = divideSpace(myFreeSpace.poll());
            if (myMaxMin[MAX_IDX] > 0) {
                myFreeSpace.add(myMaxMin[MAX_IDX]);
            }
            if (myMaxMin[MIN_IDX] > 0) {
                myFreeSpace.add(myMaxMin[MIN_IDX]);
            }
        }
        return divideSpace(myFreeSpace.poll());
    }

    private static long[] divideSpace(Long spaceSize) {
        if (spaceSize % 2 == 1) {
            return new long[] {(spaceSize - 1)/ 2, (spaceSize - 1)/ 2};
        }
        return new long[] {spaceSize / 2, (spaceSize - 2) / 2};
    }

    private static long[] readInts(Scanner aIn) {
        long[] myBffs = new long[2];
        String[] myInput = aIn.nextLine().split(" ");
        for (int i = 0; i < myInput.length; i++) {
            myBffs[i] = Long.valueOf(myInput[i]);
        }
        return myBffs;
    }
}

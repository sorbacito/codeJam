package com.sorbac.codeJam._2017.qualification.tidyNumbers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            Long myNumber = in.nextLong();
            long myTidyNumber = generateBiggestTidyNymber(myNumber);
            System.out.println("Case #" + i + ": " + myTidyNumber);
        }
    }

    public static long generateBiggestTidyNymber(Long myNumber) {
        Integer[] myDigits = decomposeNumber(myNumber);
        return generateBiggestTidyNymber(myDigits);
    }

    public static long generateBiggestTidyNymber(Integer[] myDigits) {
        int firsDigitIndexBrakingTidiness = getFirsDigitIndexBrakingTidiness(myDigits);
        if (firsDigitIndexBrakingTidiness == -1) { // Number is tidy
            return composeNumber(myDigits);
        } else {
            if (myDigits[firsDigitIndexBrakingTidiness] > 1) {
                myDigits[firsDigitIndexBrakingTidiness] -= 1;
                long myFirstPart = generateBiggestTidyNymber(composeNumber(0, firsDigitIndexBrakingTidiness + 1, myDigits));
                for (int i = 0; i < myDigits.length - firsDigitIndexBrakingTidiness - 1; i++) {
                    myFirstPart *= 10;
                }
                for (int i = firsDigitIndexBrakingTidiness + 1; i < myDigits.length; i++) {
                    myDigits[i] = 9;
                }
                return myFirstPart + composeNumber(firsDigitIndexBrakingTidiness + 1, myDigits);
            } else { // Number is starting with one or many 1 digits
                for (int i = 0; i < myDigits.length; i++) {
                    myDigits[i] = 9;
                }
                return composeNumber(1, myDigits);
            }
        }
    }

    private static long composeNumber(Integer[] myDigits) {
        return composeNumber(0, myDigits.length, myDigits);
    }

    private static long composeNumber(int startingIndex, Integer[] myDigits) {
        return composeNumber(startingIndex, myDigits.length, myDigits);
    }

    private static long composeNumber(int startingIndex, int endIndex, Integer[] myDigits) {
        long myReturnNumber = 0L;
        for (int i = startingIndex; i < endIndex; i++) {
            myReturnNumber += myDigits[i];
            if (i + 1 < endIndex) {
                myReturnNumber *= 10;
            }
        }
        return myReturnNumber;
    }

    public static int getFirsDigitIndexBrakingTidiness(Integer[] myDigits) {
        int i = 0;
        while (myDigits.length > i + 1) {
            if (myDigits[i] > myDigits[i + 1] || myDigits[i + 1] == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static Integer[] decomposeNumber(Long myNumber) {
        List<Integer> myDigits = new ArrayList<Integer>();
        while (myNumber > 0) {
            myDigits.add((int) (myNumber % 10));
            myNumber /= 10l;
        }
        Collections.reverse(myDigits);
        return myDigits.toArray(new Integer[myDigits.size()]);
    }
}

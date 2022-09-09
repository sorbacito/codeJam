package com.sorbac.codeJam.year2022.qualification.printers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            long[][] printers = readPrinters(in);
            System.out.println("Case #" + i + ": " + answerForPrinters(printers));
        }
    }

    private static String answerForPrinters(long[][] printers) {
        String answer = "";
        long maxUnits = 1_000_000;
        for (int color = 0; color < 4; color++) {
            long minForColor = getMinForColor(printers, color);
            long colorUnits = Math.min(maxUnits, minForColor);
            maxUnits -= colorUnits;
            answer += colorUnits;
            if (color != 3) {
                answer += " ";
            }
        }
        return maxUnits == 0 ? answer : "IMPOSSIBLE";
    }

    private static long getMinForColor(long[][] printers, int color) {
        return Math.min(Math.min(printers[0][color], printers[1][color]), printers[2][color]);
    }

    private static long[][] readPrinters(Scanner aIn) {
        long[][] myBffs = new long[3][4];
        for (int printer = 0; printer < 3; printer++) {
            String[] myInput = aIn.nextLine().split(" ");
            for (int i = 0; i < myInput.length; i++) {
                myBffs[printer][i] = Long.parseLong(myInput[i]);
            }
        }
        return myBffs;
    }
}

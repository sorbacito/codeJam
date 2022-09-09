package com.sorbac.codeJam.year2022.qualification.punchedCards;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            long[] myInts = readInts(in);
            System.out.println("Case #" + i + ":");
            createPunchCard(myInts[0], myInts[1]).forEach(System.out::println);
        }
    }

    private static List<String> createPunchCard(long rows, long cols) {
        List<String> myCard = new ArrayList<>();
        myCard.add(printTopBorder(cols));
        myCard.addAll(printFirstRow(cols));
        for (int i = 1; i < rows; i++) {
            myCard.addAll(printCommonRow(cols));
        }
        return myCard;
    }

    private static String printTopBorder(long cols) {
        return createLine(cols, "..", "+-", "+");
    }

    private static List<String> printFirstRow(long cols) {
        List<String> row = new ArrayList<>();
        row.add(createLine(cols, "..", "|.", "|"));
        row.add(createLine(cols, "+-", "+-", "+"));
        return row;
    }

    private static List<String> printCommonRow(long cols) {
        List<String> row = new ArrayList<>();
        row.add(createLine(cols, "|.", "|.", "|"));
        row.add(createLine(cols, "+-", "+-", "+"));
        return row;
    }

    private static String createLine(long cols, String firstCell, String commonCel, String endChar) {
        StringBuilder border = new StringBuilder(firstCell);
        for (int i = 1; i < cols; i++) {
            border.append(commonCel);
        }
        border.append(endChar);
        return border.toString();
    }

    private static long[] readInts(Scanner aIn) {
        long[] myBffs = new long[2];
        String[] myInput = aIn.nextLine().split(" ");
        for (int i = 0; i < myInput.length; i++) {
            myBffs[i] = Long.parseLong(myInput[i]);
        }
        return myBffs;
    }
}

package com.sorbac.codeJam.year2022.qualification.passages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Solution {

    private static Random random;
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int[] myInts = readInts(in);
            startInteraction(myInts[0], myInts[1]);
        }
    }

    private static void startInteraction(int rooms, int interactions) {
        random = new Random();
        int passages = 0;
        int[] visited = new int[rooms];
        for (int i = 0; i < Math.min(rooms, interactions); i++) {
            int[] answer = readInts(in);
            if (answer.length == 1) return;
            visited[answer[0] - 1] = 1;
            passages += answer[1];
            if (i != Math.min(rooms, interactions) - 1) {
                int roomToVisit = getRoomToVisit(visited);
                System.out.println("T " + (roomToVisit + 1));
            }
        }
        long estimate = BigInteger.valueOf(passages).multiply(BigInteger.valueOf(rooms)).divide(BigInteger.valueOf(Math.min(rooms, interactions))).divide(BigInteger.TWO).longValue();
        System.out.println("E " + estimate);
    }

    private static int getRoomToVisit(int[] visited) {
        int randomId = random.nextInt(visited.length);
        while (visited[randomId] != 0) {
            randomId += 1;
            randomId %= visited.length;
        }
        return randomId;
    }

    private static int[] readInts(Scanner aIn) {
        int[] myBffs = new int[2];
        String[] myInput = aIn.nextLine().split(" ");
        for (int i = 0; i < myInput.length; i++) {
            myBffs[i] = Integer.parseInt(myInput[i]);
        }
        return myBffs;
    }

}

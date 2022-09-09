package com.sorbac.codeJam.year2022.qualification.dices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            Map<Integer, Integer> diceBucket = readDiceBucket(in);
            System.out.println("Case #" + i + ": " + longestStraight(diceBucket));
        }
    }

    public static int longestStraight(Map<Integer, Integer> diceBucket) {
        LinkedHashMap<Integer, Integer> sortedBucket = new LinkedHashMap<>();
        diceBucket.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedBucket.put(x.getKey(), x.getValue()));

        int maxS = 0;

        for (Map.Entry<Integer, Integer> entry : sortedBucket.entrySet()) {
            maxS = Math.min(entry.getKey(), maxS + entry.getValue());
        }

        return maxS;
    }

    private static Map<Integer, Integer> readDiceBucket(Scanner aIn) {
        Map<Integer, Integer> diceMap = new HashMap<>();
        int t = Integer.parseInt(aIn.nextLine());
        String[] myInput = aIn.nextLine().split(" ");
        for (String s : myInput) {
            int dice = Integer.parseInt(s);
            diceMap.putIfAbsent(dice, 0);
            diceMap.merge(dice, 1, Integer::sum);
        }
        return diceMap;
    }
}

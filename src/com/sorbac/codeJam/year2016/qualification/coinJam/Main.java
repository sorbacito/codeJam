package com.sorbac.codeJam.year2016.qualification.coinJam;/*
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
 *  Time: 22:33   
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String ZERO = "0";
    public static final String PAIR_ZERO = "00";
    public static final String PAIR_ONE = "11";
    private int theCoinLength;
    public Main(int aCoinLength) {
        theCoinLength = aCoinLength;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int myCoinLength = in.nextInt();
            int myCoinToGenerate = in.nextInt();
            List<String> myCoins = new Main(myCoinLength).generateCoins(myCoinToGenerate);
            System.out.println("Case #" + i + ":");
            for (String myCoin : myCoins) {
                System.out.println(myCoin);
            }
        }
    }

    public List<String> generateCoins(int aCoinToGenerate) {
        List<String> myCoins = new ArrayList<String>(aCoinToGenerate);
        for (String myCoin : generateSpecialCoins(aCoinToGenerate)) {
            myCoins.add(myCoin + " 3 2 5 2 7 2 3 2 11");
        }
        return myCoins;
    }

    public List<String> generateSpecialCoins(int aCoinToGenerate) {
        List<String> mySpecialCoins = new ArrayList<String>();
        int myNumberOfPairs = theCoinLength - 4;
        List<List<Integer>> myPairs = generate2Pairs(myNumberOfPairs, aCoinToGenerate);
        for (List<Integer> myPair : myPairs) {
            mySpecialCoins.add(generateCoin(myPair, myNumberOfPairs));
        }
        if (mySpecialCoins.size() >= aCoinToGenerate) {
            return mySpecialCoins;
        }
        aCoinToGenerate -= mySpecialCoins.size();
        myNumberOfPairs -= 2;
        List<List<Integer>> my4Pairs = generate4Pairs(myNumberOfPairs , aCoinToGenerate);
        for (List<Integer> myPair : my4Pairs) {
            mySpecialCoins.add(generateCoin(myPair, myNumberOfPairs));
        }
        return mySpecialCoins;
    }

    private String generateCoin(List<Integer> aPositions, int aNumberOfPairs) {
        StringBuilder myCoin = new StringBuilder("1");

        for(int i = 0; i < aNumberOfPairs; i++) {
            if (aPositions.contains(i)) {
                myCoin.append(PAIR_ONE);
            } else {
                myCoin.append(ZERO);
            }
        }
        myCoin.append("1");
        return myCoin.toString();
    }

    private List<List<Integer>> generate2Pairs(int aNumberOfPairs, int aCoinToGenerate) {
        List<List<Integer>> myPairs = new ArrayList<List<Integer>>();
        for (int i = 0; i < aNumberOfPairs; i++) {
            for (int j = i + 1; j < aNumberOfPairs; j++) {
                    myPairs.add(Arrays.asList(i, j));
                    if (aCoinToGenerate <= myPairs.size()) {
                        return myPairs;
                }
            }
        }
        return myPairs;
    }

    private List<List<Integer>> generate4Pairs(int aNumberOfPairs, int aCoinToGenerate) {
        List<List<Integer>> myPairs = new ArrayList<List<Integer>>();
        for (int i = 0; i < aNumberOfPairs; i++) {
            for (int j = i + 1; j < aNumberOfPairs; j++) {
                for (int k = j + 1; k < aNumberOfPairs; k++) {
                    for (int l = k + 1; l < aNumberOfPairs; l++) {
                        myPairs.add(Arrays.asList(i, j, k, l));
                        if (aCoinToGenerate <= myPairs.size()) {
                            return myPairs;
                        }
                    }
                }
            }
        }
        return myPairs;
    }
}

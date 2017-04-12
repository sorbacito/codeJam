package com.sorbac.codeJam.year2014.qualification.cookieClickerAlpha;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int myNumberOfTestCases = Integer.parseInt(input.readLine());
        for (int i = 0; i < myNumberOfTestCases; i++) {
            TestCase myTestCase = readTestCase(input);
            out.append(String.format("Case #%1d: %2.9f", i + 1, myTestCase.calculate()));
            out.newLine();
        }
        out.flush();
    }

    private static TestCase readTestCase(BufferedReader aInput) throws IOException {
        String[] myConfiguration = aInput.readLine().split(" ");
        double myFarmPrice = Double.parseDouble(myConfiguration[0]);
        double myFarmBonus = Double.parseDouble(myConfiguration[1]);
        double myFinalCount = Double.parseDouble(myConfiguration[2]);
        return new TestCase(myFarmPrice, myFarmBonus, myFinalCount);
    }

    public static class TestCase {
        private final double theFarmPrice;
        private final double theFarmBonus;
        private final double theFinalCount;

        public TestCase(double aFarmPrice, double aFarmBonus, double aFinalCount) {
            theFarmPrice = aFarmPrice;
            theFarmBonus = aFarmBonus;
            theFinalCount = aFinalCount;
        }

        public double calculate() {
            int myFarmCount = 0;
            double myLastFarmTime = 0d;
            double myLastTime = theFinalCount / 2;
            while (true) {
                double myFarmTime = myLastFarmTime + theFarmPrice / (2 + theFarmBonus * (myFarmCount++));
                double myTime = myFarmTime + theFinalCount / (2 + theFarmBonus * myFarmCount);
                if (myTime > myLastTime) {
                    return myLastTime;
                }
                myLastFarmTime = myFarmTime;
                myLastTime = myTime;
            }
        }
    }
}

package com.sorbac.codeJam.year2014.qualification.magicTrick;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int myNumberOfTestCases = Integer.parseInt(input.readLine());
        for (int i = 0; i < myNumberOfTestCases; i++) {
            TestCase myTestCase = readTestCase(input);
            out.append(String.format("Case #%1d: %2s", i + 1, myTestCase.getAnswer()));
            out.newLine();
        }
        out.flush();
    }

    private static TestCase readTestCase(BufferedReader aInput) throws IOException {
        final int myFirstAnswer = Integer.parseInt(aInput.readLine());
        int[][] myFirstArrangement = readCards(aInput);
        final int mySecondAnswer = Integer.parseInt(aInput.readLine());
        int[][] mySecondArrangement = readCards(aInput);
        return new TestCase(myFirstArrangement, mySecondArrangement, myFirstAnswer, mySecondAnswer);
    }

    private static int[][] readCards(BufferedReader aInput) throws IOException {
        int[][] myAnswer = new int[4][4];
        for (int i = 0; i < 4; i++) {
            String[] myLine = aInput.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                myAnswer[i][j] = Integer.parseInt(myLine[j]);
            }
        }
        return myAnswer;
    }

    public static class TestCase {

        private final int[][] theFirstArrangement;
        private final int[][] theSecondArrangement;
        private final int theFirstAnswer;
        private final int theSecondAnswer;

        public TestCase(int[][] aFirstArrangement, int[][] aSecondArragement, int aFirstAnswer, int aSecondAnswer) {
            theFirstArrangement = aFirstArrangement;
            theSecondArrangement = aSecondArragement;
            theFirstAnswer = aFirstAnswer;
            theSecondAnswer = aSecondAnswer;
        }

        public String getAnswer() {
            int myFirstSet = createSet(theFirstArrangement, theFirstAnswer);
            int mySecondSet = createSet(theSecondArrangement, theSecondAnswer);
            int myAnswer = myFirstSet & mySecondSet;
            if (myAnswer == 0) {
                return "Volunteer cheated!";
            } else if ((myAnswer & (myAnswer - 1)) == 0) {
                return String.valueOf(binlog(myAnswer) + 1);
            } else {
                return "Bad magician!";
            }
        }

        private int createSet(int[][] aFirstArrangement, int aFirstAnswer) {
            int myAnswer = 0;
            for (int myCard : aFirstArrangement[aFirstAnswer - 1]) {
                myAnswer |= 1 << (myCard - 1);
            }
            return myAnswer;
        }
    }

    public static int binlog(int bits) // returns 0 for bits=0
    {
        int log = 0;
        if ((bits & 0xffff0000) != 0) {
            bits >>>= 16;
            log = 16;
        }
        if (bits >= 256) {
            bits >>>= 8;
            log += 8;
        }
        if (bits >= 16) {
            bits >>>= 4;
            log += 4;
        }
        if (bits >= 4) {
            bits >>>= 2;
            log += 2;
        }
        return log + (bits >>> 1);
    }
}

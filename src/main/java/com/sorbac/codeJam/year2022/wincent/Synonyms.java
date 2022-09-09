package com.sorbac.codeJam.year2022.wincent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Synonyms {

    public static void main(String[] args) {
        File inputFile = new File(args[0]);
        String outputFilePath = args[1];
        try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(inputFile))));
             PrintWriter out = new PrintWriter(new FileWriter(outputFilePath))) {
            int testCases = Integer.parseInt(in.nextLine());
            for (int i = 1; i <= testCases; ++i) {
                TestCase myCase = TestCase.readCase(in);
                for (String[] queryPair : myCase.getQueryPairs()) {
                    System.out.println(myCase.isPairSynonyms(queryPair[0], queryPair[1]));
                    out.println(myCase.isPairSynonyms(queryPair[0], queryPair[1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static class TestCase {
        private final Map<Integer, Set<String>> synonymGroups = new HashMap<>();
        private final Map<String, Integer> wordToSynonymGroup = new HashMap<>();
        private final List<String[]> queryPairs;
        private int synonymGroupCounter = 0;

        public TestCase(List<String[]> synonymPairs, List<String[]> queryPairs) {
            processSynonymPairs(synonymPairs);
            this.queryPairs = queryPairs;
        }

        public List<String[]> getQueryPairs() {
            return queryPairs;
        }

        private void processSynonymPairs(List<String[]> synonymPairs) {
            for (String[] synonymPair : synonymPairs) {
                int synonymGroupWord1Id = getSynonymGroup(synonymPair[0]);
                int synonymGroupWord2Id = getSynonymGroup(synonymPair[1]);
                mergeSynonymGroups(synonymGroupWord1Id, synonymGroupWord2Id);
            }
        }

        private void mergeSynonymGroups(int synonymGroupWord1Id, int synonymGroupWord2Id) {
            if (synonymGroupWord1Id == synonymGroupWord2Id) {
                return;
            }
            Set<String> synonymGroupWord1 = synonymGroups.get(synonymGroupWord1Id);
            Set<String> synonymGroupWord2 = synonymGroups.get(synonymGroupWord2Id);
            for (String wordGroup2 : synonymGroupWord2) {
                synonymGroupWord1.add(wordGroup2);
                wordToSynonymGroup.put(wordGroup2, synonymGroupWord1Id);
            }
            synonymGroups.put(synonymGroupWord2Id, null);
        }

        private int getSynonymGroup(String word) {
            if (wordToSynonymGroup.get(word) == null) {
                int groupId = synonymGroupCounter++;
                wordToSynonymGroup.put(word, groupId);
                Set<String> synGrp = new HashSet<>();
                synGrp.add(word);
                synonymGroups.put(groupId, synGrp);
                return groupId;
            } else {
                return wordToSynonymGroup.get(word);
            }
        }

        public String isPairSynonyms(String word1, String word2) {
            if (word1.equalsIgnoreCase(word2)
                || (wordToSynonymGroup.get(word1) != null &&
                    wordToSynonymGroup.get(word2) != null &&
                    wordToSynonymGroup.get(word1).intValue() == wordToSynonymGroup.get(word2).intValue())) {
                return "synonyms";
            }
            return "different";
        }

        public static TestCase readCase(Scanner in) {
            List<String[]> synonymPairs = readPairs(in);
            List<String[]> queryPairs = readPairs(in);
            return new TestCase(synonymPairs, queryPairs);
        }

        private static List<String[]> readPairs(Scanner in) {
            int pairsNumber = Integer.parseInt(in.nextLine());
            List<String[]> pairs = new ArrayList<>();
            for (int i = 0; i < pairsNumber; i++) {
                pairs.add(in.nextLine().toLowerCase().split(" "));
            }
            return pairs;
        }
    }
}

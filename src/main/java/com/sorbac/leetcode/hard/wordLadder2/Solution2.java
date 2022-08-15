package com.sorbac.leetcode.hard.wordLadder2;

import java.util.*;

public class Solution2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, Set<String>> prevShortWord = new HashMap<>();
        Set<String> level = new HashSet<>();
        level.add(beginWord);
        while (prevShortWord.get(endWord) == null && level.size() > 0) {
            Map<String, Set<String>> curPrevShort = new HashMap<>();
            for (String curWord : level) {
                for (String curAdj : getAdjWords(curWord, wordList)) {
                    if (!prevShortWord.containsKey(curAdj)) {
                        if (!curPrevShort.containsKey(curAdj)) {
                            curPrevShort.put(curAdj, new HashSet<>());
                        }
                        curPrevShort.get(curAdj).add(curWord);
                    }
                }
            }
            prevShortWord.putAll(curPrevShort);
            level = curPrevShort.keySet();
        }
        return generateLadders(prevShortWord, beginWord, endWord);
    }

    private List<List<String>> generateLadders(Map<String, Set<String>> prevShortWord, String beginWord, String endWord) {
        List<List<String>> ladders = new ArrayList<>();
        if (prevShortWord.containsKey(endWord)) {
            List<String> initList = new ArrayList<>();
            initList.add(endWord);
            ladders.add(initList);
            do {
                List<List<String>> newLevel = new ArrayList<>();
                for (List<String> curLad : ladders) {
                    String lastWord = curLad.get(curLad.size() - 1);
                    for (String curWord : prevShortWord.get(lastWord)) {
                        List<String> newLad = new ArrayList<>(curLad);
                        newLad.add(curWord);
                        newLevel.add(newLad);
                    }
                }
                ladders = newLevel;
            }
            while (!ladders.get(0).get(ladders.get(0).size() - 1).equals(beginWord));
        }
        for (List<String> revLadder : ladders) {
            Collections.reverse(revLadder);
        }

        return ladders;
    }

    private Set<String> getAdjWords(String word, List<String> wordList) {
        Set<String> adjWords = new HashSet<>();
        for(String curWord : wordList) {
            if (!word.equals(curWord) && isAdjancent(word, curWord)) {
                adjWords.add(curWord);
            }
        }
        return adjWords;
    }

    private boolean isAdjancent(String word1, String word2) {
        int min = Math.min(word1.length(), word2.length());
        int max = Math.max(word1.length(), word2.length());
        int diff = max - min;
        if(diff <= 1) {
            for(int i = 0; i < min; i++) {
                if(word1.charAt(i) != word2.charAt(i)) {
                    diff++;
                }
                if (diff > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}

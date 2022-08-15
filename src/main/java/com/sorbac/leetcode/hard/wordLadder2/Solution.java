package com.sorbac.leetcode.hard.wordLadder2;

import java.util.*;

public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ladders = new ArrayList<>();
        Map<String, Integer> wordLevel = new HashMap<>();
        if(wordList.contains(endWord)) {
            List<List<String>> level = new ArrayList<>();
            int levelCount = 0;
            List<String> initList = new ArrayList<>();
            initList.add(beginWord);
            level.add(initList);
            List<List<String>> newLevel = new ArrayList<>();
            for(String adjWord : getAdjWords(beginWord, wordList)) {
                List<String> list = new ArrayList<>(initList);
                list.add(adjWord);
                wordLevel.put(adjWord, levelCount);
                newLevel.add(list);
                if(adjWord.equals(endWord)) {
                    ladders.add(list);
                    return ladders;
                }
            }
            level = newLevel;
            levelCount++;

            Map<String, Set<String>> wordToAdj = createWordToAdj(wordList);
            while(ladders.size() == 0 && level.size() > 0) {
                newLevel = new ArrayList<>();
                for(List<String> curList : level) {
                    String lastWord = curList.get(curList.size() - 1);
                    for(String curAdjWord : wordToAdj.get(lastWord)) {
                        if (!curList.contains(curAdjWord)) {
                            if (wordLevel.get(curAdjWord) == null || wordLevel.get(curAdjWord) == levelCount) {
                                List<String> newList = new ArrayList<>(curList);
                                newList.add(curAdjWord);
                                wordLevel.put(curAdjWord, levelCount);
                                newLevel.add(newList);
                                if(endWord.equals(curAdjWord)) {
                                    ladders.add(newList);
                                }
                            }
                        }
                    }
                }
                level = newLevel;
                levelCount++;
            }
        }
        return ladders;
    }

    private Map<String, Set<String>> createWordToAdj(List<String> wordList) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String word : wordList) {
            map.put(word, getAdjWords(word, wordList));
        }
        return map;
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

package com.sorbac.codeJam.year2022.r1c.letterBlocks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    public static final String IMPOSSIBLE = "IMPOSSIBLE";
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            List<Tower> myInput = readTowers(in);
            System.out.println("Case #" + i + ": " + answer(myInput));
        }
    }

    private static String answer(List<Tower> towers) {
        if (towers == null || twoSameCharsUnresolvable(towers)) {
            return IMPOSSIBLE;
        }

        int lettersNum = 'Z' - 'A' + 1;
        List<Tower> singles = new ArrayList<>(lettersNum);
        List<Tower> starts = new ArrayList<>(lettersNum);
        List<Tower> ends = new ArrayList<>(lettersNum);
        for (Tower t : towers) {
            if (t.first == t.last) {
                singles.set(t.first - 'A', t);
            } else {
                int firstIdx = t.first - 'A';
                int lastIdx = t.last - 'A';
                if (starts.get(firstIdx) != null || ends.get(lastIdx) != null) {
                    return IMPOSSIBLE;
                }
                starts.set(firstIdx, t);
                ends.set(lastIdx, t);
            }
        }

//        Deque<List<Tower>> stack = new ArrayDeque<>();
//        stack.addFirst(towers);
//        Set<String> tried = new HashSet<>();
//
//        while (!stack.isEmpty()) {
//            List<Tower> curTowers = stack.pollFirst();
//            for (Tower t : curTowers) {
//                List<Tower> possibleTowers;
//
//                Map<Character, List<Tower>> restTowersByFirst = indexByFirst(curTowers);
//                if (restTowersByFirst.get(t.getLast()) != null) {
//                    possibleTowers = restTowersByFirst.get(t.getLast());
//                    possibleTowers.remove(t);
//                } else {
//                    possibleTowers = new ArrayList<>(curTowers);
//                    possibleTowers.remove(t);
//                }
//                for (Tower tryTower : possibleTowers) {
//                    List<Tower> mTowers = mergeTowers(t, tryTower);
//                    if (!mTowers.isEmpty()) {
//                        for (Tower mTower : mTowers) {
//                            List<Tower> newTowers = new ArrayList<>(curTowers);
//                            newTowers.remove(tryTower);
//                            newTowers.remove(t);
//                            if (newTowers.size() == 0) {
//                                return mTower.getBlocks();
//                            }
//                            newTowers.add(mTower);
//                            if (!tried.contains(mTower.getBlocks())) {
//                                stack.addFirst(newTowers);
//                                tried.add(mTower.getBlocks());
//                            }
//                        }
//                    }
//                }
//            }
//        }

        return IMPOSSIBLE;
    }

    private static boolean twoSameCharsUnresolvable(List<Tower> towers) {
        Set<Character> unresolvable = new HashSet<>();
        for (Tower t : towers) {
            for (char c : t.getUnresolvable()) {
                if (unresolvable.contains(c)) {
                    return true;
                }
                unresolvable.add(c);
            }
        }
        return false;
    }

    private static Map<Character, List<Tower>> indexByFirst(List<Tower> curTowers) {
        Map<Character, List<Tower>> map = new HashMap<>();
        for (Tower t : curTowers) {
            if (map.get(t.first) == null) {
                List<Tower> l = new ArrayList<>();
                l.add(t);
                map.put(t.first, l);
            } else {
                map.get(t.first).add(t);
            }
        }
        return map;
    }

    private static List<Tower> mergeTowers(Tower t1, Tower t2) {
        List<Tower> result = new ArrayList<>();
        Tower res1 = readTower(t1.blocks + t2.blocks);
        if (res1 != null) {
            result.add(res1);
        }
        Tower res2 = readTower(t1.blocks + t2.blocks);
        if (res2 != null) {
            result.add(res2);
        }
        return result;
    }

    private static List<Tower> readTowers(Scanner aIn) {
        aIn.nextLine();
        String[] myInput = aIn.nextLine().split(" ");
        List<Tower> towers = new ArrayList<>();
        for (String s : myInput) {
            Tower t = readTower(s);
            if (t == null) {
                return null;
            }
            towers.add(t);
        }
        return towers;    }

    private static Tower readTower(String towerString) {
        Tower result = new Tower(towerString);
        char cur = '0';
        char[] towerChars = towerString.toCharArray();
        for(char c : towerChars) {
            if (result.contains(c) && cur != c) {
                return null;
            }
            result.addChar(c);
            cur = c;
        }
        result.setFirst(towerChars[0]);
        result.setLast(towerChars[towerChars.length - 1]);
        return result;
    }

    private static class Tower {
        String blocks;
        Set<Character> chars;
        Set<Character> unresolvable = null;
        char first;
        char last;

        public Tower(String blocks) {
            this.blocks = blocks;
            this.chars = new HashSet<>();
        }

        public String getBlocks() {
            return blocks;
        }

        public void setBlocks(String blocks) {
            this.blocks = blocks;
        }

        public char getFirst() {
            return first;
        }

        public void setFirst(char first) {
            this.first = first;
        }

        public char getLast() {
            return last;
        }

        public void setLast(char last) {
            this.last = last;
        }

        public boolean contains(char c) {
            return chars.contains(c);
        }

        public void addChar(char c) {
            chars.add(c);
        }

        public Set<Character> getUnresolvable() {
            if (unresolvable == null) {
                unresolvable = new HashSet<>(chars);
                unresolvable.remove(first);
                unresolvable.remove(last);
            }
            return unresolvable;
        }
    }
}

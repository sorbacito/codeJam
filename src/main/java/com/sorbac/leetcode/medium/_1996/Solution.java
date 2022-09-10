package com.sorbac.leetcode.medium._1996;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        TreeMap<Integer, PriorityQueue<Integer>> treeMap = new TreeMap<>();
        for (int[] property : properties) {
            treeMap.computeIfAbsent(property[0], k -> new PriorityQueue<>(Comparator.reverseOrder()));
            treeMap.get(property[0]).add(property[1]);
        }
        int max = 0;
        int weak = 0;
        for (Integer integer : treeMap.descendingKeySet()) {
            PriorityQueue<Integer> maxHeapDefense = treeMap.get(integer);
            int heapMax = maxHeapDefense.peek();
            while (maxHeapDefense.size() > 0 && maxHeapDefense.peek() >= max) {
                maxHeapDefense.poll();
            }
            weak += maxHeapDefense.size();
            max = Math.max(max, heapMax);
        }
        return weak;
    }

}

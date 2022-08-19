package com.sorbac.leetcode.easy._118;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/pascals-triangle">https://leetcode.com/problems/pascals-triangle</a>
 */
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalT = new ArrayList<>();
        pascalT.add(List.of(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> lastRow = pascalT.get(pascalT.size() - 1);
            List<Integer> newRow = new ArrayList<>();
            newRow.add(1);
            for (int j = 0; j < lastRow.size() - 1; j++) {
                newRow.add(lastRow.get(j) + lastRow.get(j+1));
            }
            newRow.add(1);
            pascalT.add(newRow);
        }
        return pascalT;
    }
}

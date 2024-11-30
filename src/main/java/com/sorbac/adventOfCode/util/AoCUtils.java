package com.sorbac.adventOfCode.util;

import java.util.*;

public enum AoCUtils {
    ;

    public static <T> Collection<List<T>> allPermutations(Collection<T> values) {
        Iterator<T> iterator = values.iterator();
        Collection<List<T>> permutations = new HashSet<>();
        permutations.add(new LinkedList<>());
        while (iterator.hasNext()) {
            T val = iterator.next();
            Collection<List<T>> curPermutations = new HashSet<>();
            for (List<T> permutation : permutations) {
                for (int i = 0; i <= permutation.size(); i++) {
                    List<T> newPermutation = new LinkedList<>(permutation);
                    newPermutation.add(i, val);
                    curPermutations.add(newPermutation);
                }
            }
            permutations = curPermutations;
        }
        return permutations;
    }

    public static Collection<List<Integer>> allCombinationsOfSum(int parts, int sum) {
        Collection<List<Integer>> combinations = new ArrayList<>();
        combinations.add(List.of());
        for (int i = 0; i < parts - 1; i++) {
            Collection<List<Integer>> newCombinations = new ArrayList<>();
            for (List<Integer> combination : combinations) {
                for (int j = 0; j <= sum - combination.stream().mapToInt(Integer::intValue).sum(); j++) {
                    List<Integer> newCombination = new ArrayList<>(combination);
                    newCombination.add(j);
                    newCombinations.add(newCombination);
                }
            }
            combinations = newCombinations;
        }
        Collection<List<Integer>> newCombinations = new ArrayList<>();
        for (List<Integer> combination : combinations) {
            List<Integer> newCombination = new ArrayList<>(combination);
            newCombination.add(sum - combination.stream().mapToInt(Integer::intValue).sum());
            newCombinations.add(newCombination);
        }
        combinations = newCombinations;
        return combinations;
    }
}

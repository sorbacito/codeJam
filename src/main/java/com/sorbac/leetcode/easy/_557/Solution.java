package com.sorbac.leetcode.easy._557;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
    public String reverseWords(String s) {
        return Arrays.stream(s.split(" ")).parallel()
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));
    }
}

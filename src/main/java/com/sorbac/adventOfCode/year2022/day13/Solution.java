package com.sorbac.adventOfCode.year2022.day13;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day13/input.txt";
    public static final String TEST_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day13/test.txt";

    private final List<Object> left;
    private final List<Object> right;

    public Solution(Pair left, Pair right) {
        this.left = (List<Object>) left.result;
        this.right = (List<Object>) right.result;
    }

    public static Pair readItem(String line) {
        return readItem(line, 0);
    }
    public static Pair readItem(String line, Integer idx) {
        if (Character.isDigit(line.charAt(idx))) {
            return readInt(line, idx);
        } else {
            idx++;
            List<Object> list = new ArrayList<>();
            while (line.charAt(idx) != ']') {
                if (line.charAt(idx) == ',') idx = idx + 1;
                Pair item = readItem(line, idx);
                idx = item.idx;
                list.add(item.result);
            }
            idx++;
            return new Pair(list, idx);
        }
    }

    private static Pair readInt(String line, Integer idx) {
        int result = 0;
        while(Character.isDigit(line.charAt(idx))) {
            result *= 10;
            result += (line.charAt(idx++) - '0');
        }
        return new Pair(result, idx);
    }

    private record Pair(Object result, int idx) {}

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
        int sum = 0;
        int i = 1;
        List<Object> lists = new ArrayList<>();
        List<List<Integer>> packet2 = List.of(List.of(2));
        lists.add(packet2);
        List<List<Integer>> packet6 = List.of(List.of(6));
        lists.add(packet6);
        while (in.hasNextLine()) {
            Pair line1 = readItem(in.nextLine(), 0);
            Pair line2 = readItem(in.nextLine(), 0);
            lists.add(line1.result);
            lists.add(line2.result);
            if (in.hasNextLine()) in.nextLine();
            if (new Solution(line1, line2).isInRightOrder()) {
                sum += i;
            }
            i++;
        }
        System.out.println(sum);
        lists.sort((o1, o2) -> compareLists((List<Object>) o1, (List<Object>) o2));
        int decoder = 1;
        for (int j = 0; j < lists.size(); j++) {
            if (lists.get(j) == packet2 || lists.get(j) == packet6) {
                decoder *= j + 1;
            }
        }
        System.out.println(decoder);
    }

    public boolean isInRightOrder() {
        return compareLists(this.left, this.right) < 0;
    }

    private static int compareLists(List<Object> left, List<Object> right) {
        int idx = 0;
        int sizeLeft = left.size();
        int sizeRight = right.size();
        int maxIdx = Math.min(sizeLeft, sizeRight);
        int comp;
        while (idx < maxIdx) {
            Object curLeft = left.get(idx);
            Object curRight = right.get(idx);
            if (curLeft instanceof List<?> && curRight instanceof List<?>) {
                comp = compareLists((List<Object>) curLeft, (List<Object>) curRight);
            } else if (curLeft instanceof Integer && curRight instanceof Integer) {
                comp = ((Integer) curLeft).compareTo((Integer) curRight);
            } else {
                if (curLeft instanceof Integer) {
                    comp = compareLists(List.of(curLeft), (List<Object>) curRight);
                } else {
                    comp = compareLists((List<Object>) curLeft, List.of(curRight));
                }
            }
            if (comp != 0) return comp;
            idx++;
        }
        return sizeLeft - sizeRight;
    }
}

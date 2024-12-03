package com.sorbac.adventOfCode.year2018.day;

import com.sorbac.adventOfCode.common.Pair;
import com.sorbac.adventOfCode.year2018.Day2018;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day2 extends Day2018 {
    private static final int DAY = Integer.parseInt(Day2.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2() {
        super(DAY);
    }

    protected Day2(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day2().printParts();
        new Day2(String.join(DEFAULT_DELIMITER,
                "abcdef",
                "bababc",
                "abbcde",
                "abcccd",
                "aabcdd",
                "abcdee",
                "ababab")).printPart1(12);
        new Day2("abcde\n" +
                "fghij\n" +
                "klmno\n" +
                "pqrst\n" +
                "fguij\n" +
                "axcye\n" +
                "wvxyz").printPart2("fgij");
    }

    @Override
    protected Object part1() {
        Pair<Integer, Integer> reducedPair = dayStreamLines().map(
                line -> new Pair<>(hasExactlyNOfAnyLetter(line, 2), hasExactlyNOfAnyLetter(line, 3))
        ).reduce(
                new Pair<>(0, 0),
                (pair1, pair2) -> new Pair<>(pair1.getFirst() + pair2.getFirst(), pair1.getSecond() + pair2.getSecond())
        );
        return reducedPair.getFirst() * reducedPair.getSecond();
    }

    private int hasExactlyNOfAnyLetter(String line, int i) {
        Map<Integer, Long> map = line.chars().boxed().collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        return map.values().stream().anyMatch(count -> count == i) ? 1 : 0;
    }

    @Override
    protected Object part2() {
        List<String> ids = dayStreamLines().toList();
        for (int i = 0; i < ids.size(); i++) {
            for (int j = i + 1; j < ids.size(); j++) {
                String commonLetters = commonLetters(ids.get(i), ids.get(j));
                if (commonLetters.length() == ids.get(i).length() - 1) {
                    return commonLetters;
                }
            }
        }
        return null;
    }

    private String commonLetters(String first, String second) {
        StringBuilder commonLetters = new StringBuilder();
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) == second.charAt(i)) {
                commonLetters.append(first.charAt(i));
            }
        }
        return commonLetters.toString();
    }
}

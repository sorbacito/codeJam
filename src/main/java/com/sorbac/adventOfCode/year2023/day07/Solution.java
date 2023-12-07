package com.sorbac.adventOfCode.year2023.day07;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day07/test1.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day07/input1.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day07/test2.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day07/input2.txt"))));

        List<String> lines1 = new ArrayList<>();
        while (in1.hasNextLine()) {
            lines1.add(in1.nextLine());
        }
        long part1Answer = getPart1Answer(lines1);
        System.out.println(part1Answer);

        List<String> lines2 = new ArrayList<>();
        while (in2.hasNextLine()) {
            lines2.add(in2.nextLine());
        }
        long part2Answer = getPart2Answer(lines2);
        System.out.println(part2Answer);
    }

    private static long getPart1Answer(List<String> lines) {
        List<Hand> hands = new ArrayList<>();
        for (String line : lines) {
            hands.add(new Hand(line));
        }
        Collections.sort(hands);
        long sum = 0;
        for (int i = 0; i < hands.size(); i++) {
            sum += hands.get(i).bid * (i + 1);
        }

        return sum;
    }

    private static long getPart2Answer(List<String> lines) {
        List<Hand2> hands = new ArrayList<>();
        for (String line : lines) {
            hands.add(new Hand2(line));
        }
        Collections.sort(hands);
        long sum = 0;
        for (int i = 0; i < hands.size(); i++) {
            sum += hands.get(i).bid * (i + 1);
        }

        return sum;

    }


    private static class Hand implements Comparable<Hand> {
        private static Map<Character, Integer> cards = new HashMap<>();
        static {
            cards.put('2', 0);
            cards.put('3', 1);
            cards.put('4', 2);
            cards.put('5', 3);
            cards.put('6', 4);
            cards.put('7', 5);
            cards.put('8', 6);
            cards.put('9', 7);
            cards.put('T', 8);
            cards.put('J', 9);
            cards.put('Q', 10);
            cards.put('K', 11);
            cards.put('A', 12);
        }
        Type type;
        String hand;
        long bid;

        public Hand(String hand) {
            String[] split = hand.split(" ");
            this.bid = Long.parseLong(split[1]);
            this.hand = split[0];
            this.type = Type.getType(split[0]);
        }

        private int compareByCards(Hand o1, Hand o2) {
            for (int i = 0; i < o1.hand.length(); i++) {
                int cardCompare = Integer.compare(cards.get(o1.hand.charAt(i)), cards.get(o2.hand.charAt(i)));
                if (cardCompare != 0) {
                    return cardCompare;
                }
            }
            return 0;
        }

        @Override
        public int compareTo(Hand o) {
            int typeCompare = Integer.compare(this.type.strength, o.type.strength);
            return typeCompare != 0 ? typeCompare : compareByCards(this, o);
        }
    }

    @Getter
    private enum Type {
        FIVE_OF_KIND(6),
        FOUR_OF_KIND(5),
        FULL_HOUSE(4),
        THREE_OF_KIND(3),
        TWO_PAIRS(2),
        ONE_PAIR(1),
        HIGH_CARD(0);

        private final int strength;

        Type(int strength) {
            this.strength = strength;
        }
        public static Type getType(String hand) {
            Map<Character, Integer> counts = new HashMap<>();
            for(char c : hand.toCharArray()) {
                counts.merge(c, 1, Integer::sum);
            }
            List<Integer> values = new ArrayList<>(counts.values().stream().toList());
            values.sort(Comparator.reverseOrder());
            if (values.get(0) == 5) {
                return FIVE_OF_KIND;
            } else if (values.get(0) == 4) {
                return FOUR_OF_KIND;
            } else if (values.get(0) == 3 && values.get(1) == 2) {
                return FULL_HOUSE;
            } else if (values.get(0) == 3) {
                return THREE_OF_KIND;
            } else if (values.get(0) == 2 && values.get(1) == 2) {
                return TWO_PAIRS;
            } else if (values.get(0) == 2) {
                return ONE_PAIR;
            } else {
                return HIGH_CARD;
            }
        }
    }

    private static class Hand2 implements Comparable<Hand2> {
        private static Map<Character, Integer> cards = new HashMap<>();
        static {
            cards.put('2', 0);
            cards.put('3', 1);
            cards.put('4', 2);
            cards.put('5', 3);
            cards.put('6', 4);
            cards.put('7', 5);
            cards.put('8', 6);
            cards.put('9', 7);
            cards.put('T', 8);
            cards.put('J', -1);
            cards.put('Q', 10);
            cards.put('K', 11);
            cards.put('A', 12);
        }
        Type2 type;
        String hand;
        long bid;

        public Hand2(String hand) {
            String[] split = hand.split(" ");
            this.bid = Long.parseLong(split[1]);
            this.hand = split[0];
            this.type = Type2.getType(split[0]);
        }

        private int compareByCards(Hand2 o1, Hand2 o2) {
            for (int i = 0; i < o1.hand.length(); i++) {
                int cardCompare = Integer.compare(cards.get(o1.hand.charAt(i)), cards.get(o2.hand.charAt(i)));
                if (cardCompare != 0) {
                    return cardCompare;
                }
            }
            return 0;
        }

        @Override
        public int compareTo(Hand2 o) {
            int typeCompare = Integer.compare(this.type.strength, o.type.strength);
            return typeCompare != 0 ? typeCompare : compareByCards(this, o);
        }
    }

    @Getter
    private enum Type2 {
        FIVE_OF_KIND(6),
        FOUR_OF_KIND(5),
        FULL_HOUSE(4),
        THREE_OF_KIND(3),
        TWO_PAIRS(2),
        ONE_PAIR(1),
        HIGH_CARD(0);

        private final int strength;

        Type2(int strength) {
            this.strength = strength;
        }
        public static Type2 getType(String hand) {
            Map<Character, Integer> counts = new HashMap<>();
            int jCount = 0;
            for(char c : hand.toCharArray()) {
                if (c == 'J') {
                    jCount++;
                } else {

                    counts.merge(c, 1, Integer::sum);
                }
            }
            List<Integer> values = new ArrayList<>(counts.values().stream().toList());
            values.sort(Comparator.reverseOrder());
            if (jCount == 5 || (values.get(0) + jCount == 5) || values.get(0) == 5) {
                return FIVE_OF_KIND;
            } else if ((values.get(0) + jCount == 4) || values.get(0) == 4) {
                return FOUR_OF_KIND;
            } else if ((values.get(0) + jCount == 3 && values.get(1) == 2)
                    || (values.get(0) == 3 && values.get(1) + jCount == 2)
                    || (values.get(0) == 3 && values.get(1) == 2)) {
                return FULL_HOUSE;
            } else if ((values.get(0) + jCount == 3) ||values.get(0) == 3) {
                return THREE_OF_KIND;
            } else if ((values.get(0) + jCount == 2 && values.get(1) == 2)
                    || (values.get(0) == 2 && values.get(1) + jCount == 2)
                    || (values.get(0) == 2 && values.get(1) == 2)) {
                return TWO_PAIRS;
            } else if ((values.get(0) + jCount == 2) ||values.get(0) == 2) {
                return ONE_PAIR;
            } else {
                return HIGH_CARD;
            }
        }
    }

}

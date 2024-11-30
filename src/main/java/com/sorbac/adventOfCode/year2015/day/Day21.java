package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

public class Day21 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day21.class.getSimpleName().replaceFirst("Day", ""));

    protected Day21() {
        super(DAY);
    }

    static Item EMPTY = new Item("Empty", 0, 0, 0);

    static List<Item> weapons = List.of(
            new Item("Dagger", 8, 4, 0),
            new Item("Shortsword", 10, 5, 0),
            new Item("Warhammer", 25, 6, 0),
            new Item("Longsword", 40, 7, 0),
            new Item("Greataxe", 74, 8, 0)
    );

    static List<Item> armor = List.of(
            new Item("Leather", 13, 0, 1),
            new Item("Chainmail", 31, 0, 2),
            new Item("Splintmail", 53, 0, 3),
            new Item("Bandedmail", 75, 0, 4),
            new Item("Platemail", 102, 8, 5)
    );

    static List<Item> rings = List.of(
            new Item("Damage +1", 25, 1, 0),
            new Item("Damage +2", 50, 2, 0),
            new Item("Damage +3", 100, 3, 0),
            new Item("Defense +1", 20, 0, 1),
            new Item("Defense +2", 40, 0, 2),
            new Item("Defense +3", 80, 0, 3)
    );

    protected Day21(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        Character boss = new Character("Boss", 104, 8, 1);
        Character player = new Character("Player", 100, 0, 0);
        new Day21().setArgument(List.of(boss, player)).printParts(78, 148);
    }

    @Override
    protected Object part1() {
        Set<Set<Item>> possibleItems = allCombinationsOfPossibleItems();
        Character boss = ((List<Character>) getArgument()).get(0);
        Character player = ((List<Character>) getArgument()).get(1);
        return possibleItems.stream()
                .map(items -> player.addItems(items.toArray(Item[]::new)))
                .filter(playerWithItems -> playerWithItems.winAgainst(boss))
                .mapToInt(Character::getCost)
                .min().orElseThrow();
    }

    private Set<Set<Item>> allCombinationsOfPossibleItems() {
        return allCombinationsOf(weapons, new ArrayList<>(armor) {{
                    add(EMPTY);
                }},
                new ArrayList<>(rings) {{
                    add(EMPTY);
                }}, new ArrayList<>(rings) {{
                    add(EMPTY);
                }});
    }

    @Override
    protected Object part2() {
        Set<Set<Item>> possibleItems = allCombinationsOfPossibleItems();
        Character boss = ((List<Character>) getArgument()).get(0);
        Character player = ((List<Character>) getArgument()).get(1);
        return possibleItems.stream()
                .map(items -> player.addItems(items.toArray(Item[]::new)))
                .filter(playerWithItems -> !playerWithItems.winAgainst(boss))
                .mapToInt(Character::getCost)
                .max().orElseThrow();
    }

    private Set<Set<Day21.Item>> allCombinationsOf(List<Day21.Item>... lists) {
        Set<Set<Day21.Item>> combinations = new HashSet<>();
        combinations.add(new HashSet<>());
        for (List<Day21.Item> list : lists) {
            Set<Set<Day21.Item>> newCombinations = new HashSet<>();
            for (Set<Day21.Item> combination : combinations) {
                for (Day21.Item element : list) {
                    Set<Item> newCombination = new HashSet<>(combination);
                    if (element != EMPTY) newCombination.add(element);
                    newCombinations.add(newCombination);
                }
            }
            combinations = newCombinations;
        }
        return combinations;
    }

    @RequiredArgsConstructor
    private static class Character {
        private final String name;
        private final int hitPoints;
        private final int damage;
        private final int armor;
        @Getter
        private int cost = 0;

        public Character addItems(Item... items) {
            Character character = new Character(name, hitPoints,
                    Arrays.stream(items).mapToInt(Item::damage).sum() + damage,
                    Arrays.stream(items).mapToInt(Item::armor).sum() + armor);
            character.cost = Arrays.stream(items).mapToInt(Item::cost).sum() + cost;
            return character;
        }

        public boolean winAgainst(Character other) {
            int meRounds = Math.ceilDiv(hitPoints, Math.max(1, other.damage - armor));
            int otherRounds = Math.ceilDiv(other.hitPoints, Math.max(1, damage - other.armor));
            return meRounds >= otherRounds;
        }
    }

    private record Item(String name, int cost, int damage, int armor) {
//        final String name;
//        final int cost;
//        final int damage;
//        final int armor;
    }
}

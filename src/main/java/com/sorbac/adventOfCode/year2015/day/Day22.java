package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.common.Pair;
import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.*;
import java.util.stream.Collectors;

public class Day22 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day22.class.getSimpleName().replaceFirst("Day", ""));

    protected Day22() {
        super(DAY);
    }

    protected Day22(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
//        new Day22().setArgument(List.of(new Character(10, 0, 0, 250),
//                new Character(13, 0, 8, 0))).printParts( 226);
//
//        new Day22().setArgument(List.of(new Character(10, 0, 0, 250),
//                new Character(14, 0, 8, 0))).printParts( Spell.RECHARGE.manaCost + Spell.SHIELD.manaCost + Spell.DRAIN.manaCost + Spell.POISON.manaCost + Spell.MAGIC_MISSILE.manaCost);

        new Day22().setArgument(List.of(new Character(50, 0, 0, 500),
                new Character(51, 0, 9, 0))).printParts(); // 1538 too high 1591
    }

    @Override
    protected Object part1() {
        Queue<Pair<Character, Character>> games = new LinkedList<>();
        List<Character> characterList = (List<Character>) getArgument();
        games.add(new Pair<>(
                characterList.get(0),
                characterList.get(1)
        ));
        int minMana = Integer.MAX_VALUE;
        while (!games.isEmpty()) {
            Pair<Character, Character> game = games.poll();
            Character player = game.left();
            Character boss = game.right();
            for (Spell spell : Spell.values()) {
                if (minMana > player.manaCost + spell.manaCost && player.canAddSpell(spell)) {
                    Character curPlayer = player.copy();
                    Character curBoss = boss.copy();
                    GameResult gameResult = curPlayer.doTwoTurns(curBoss, spell);
                    if (gameResult == GameResult.WIN) {
                        minMana = Math.min(minMana, curPlayer.manaCost);
                    }
                    if (gameResult == GameResult.CONTINUE) {
                        games.add(new Pair<>(curPlayer, curBoss));
                    }
                }
            }
        }
        return minMana;
    }

    @Override
    protected Object part2() {
        return null;
    }

    private static class Character {
        private int hitPoints;
        private int armor;
        private int damage;
        private int mana;
        private int manaCost = 0;
        private Map<Spell, Integer> spellTimers = new HashMap<>();

        public Character(int hitPoints, int armor, int damage, int mana) {
            this.hitPoints = hitPoints;
            this.armor = armor;
            this.damage = damage;
            this.mana = mana;
        }

        public Character copy() {
            Character character = new Character(hitPoints, armor, damage, mana);
            character.manaCost = manaCost;
            character.spellTimers = spellTimers.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            return character;
        }

        public boolean canAddSpell(Spell spell) {
            return (!spellTimers.containsKey(spell) || spellTimers.get(spell) == 1)
                    && spell.manaCost <= mana;
        }

        public GameResult doTwoTurns(Character boss, Spell spell) {
            // Player turn
            mana -= spell.manaCost;
            manaCost += spell.manaCost;
            if (!spell.isEffectSpell()) {
                boss.hitPoints -= spell.damage;
                hitPoints += spell.heal;
            }

            processSpells(boss);

            spellTimers.put(spell, spell.duration - 1);

//            boss.hitPoints -= Math.max(1, damage - boss.armor);
            if (boss.hitPoints <= 0) {
                return GameResult.WIN;
            }
            // Boss turn

            processSpells(boss);
            hitPoints -= Math.max(1, boss.damage - armor);
            if (boss.hitPoints <= 0) {
                return GameResult.WIN;
            } else if (hitPoints <= 0) {
                return GameResult.LOSE;
            } else return GameResult.CONTINUE;
        }

        private void processSpells(Character boss) {
            armor = 0;

            List<Spell> removeSpells = spellTimers.entrySet().stream()
                    .filter(entry -> entry.getValue() == 0)
                    .map(Map.Entry::getKey).toList();
            removeSpells.forEach(spellTimers::remove);

            spellTimers.entrySet().forEach(entry -> {
                armor += entry.getKey().armor;
                boss.hitPoints -= entry.getKey().damage;
                mana += entry.getKey().manaAdd;
                entry.setValue(entry.getValue() - 1);
            });
        }
    }

    private enum GameResult {
        WIN, LOSE, CONTINUE
    }

    private enum Spell {
        MAGIC_MISSILE(53, 4, 0, 0, 0, 1),
        DRAIN(73, 2, 2, 0, 0, 1),
        SHIELD(113, 0, 0, 7, 0, 6),
        POISON(173, 3, 0, 0, 0, 6),
        RECHARGE(229, 0, 0, 0, 101, 5);

        private final int manaCost;
        private final int damage;
        private final int heal;
        private final int armor;
        private final int manaAdd;
        private final int duration;

        Spell(int manaCost, int damage, int heal, int armor, int manaAdd, int duration) {
            this.manaCost = manaCost;
            this.damage = damage;
            this.heal = heal;
            this.armor = armor;
            this.manaAdd = manaAdd;
            this.duration = duration;
        }

        public boolean isEffectSpell() {
            return duration > 1;
        }
    }
}

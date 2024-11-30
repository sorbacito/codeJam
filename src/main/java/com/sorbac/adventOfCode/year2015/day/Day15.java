package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.util.AoCUtils;
import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.sorbac.adventOfCode.util.AoCUtils.allPermutations;

public class Day15 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day15.class.getSimpleName().replaceFirst("Day", ""));

    protected Day15() {
        super(DAY);
    }

    protected Day15(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day15(String.join(DEFAULT_DELIMITER,
                "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8",
                "Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3")).printParts(62842880, 57600000);
        new Day15().printParts(18965440, 15862900);
    }

    @Override
    protected Object part1() {
        return solvePart(l -> true);
    }

    @Override
    protected Object part2() {
        return solvePart(ingredients -> ingredients.stream().mapToInt(i -> i.calories).sum() == 500);

    }

    private Integer solvePart(Predicate<List<Ingredient>> isIngredientsValid) {
        List<Ingredient> ingredients = dayStream().map(Ingredient::parse).toList();
        Collection<List<Integer>> amountsCombinations = AoCUtils.allCombinationsOfSum(ingredients.size(), 100);
        return allPermutations(ingredients).stream()
                .flatMap(ingredientList -> amountsCombinations.stream()
                        .map(amount -> totalScore(IntStream.range(0, ingredients.size())
                                .mapToObj(i -> ingredientList.get(i).multiply(amount.get(i)))
                                .toList(), isIngredientsValid))
                ).max(Integer::compareTo).orElse(-1);
    }

    private int totalScore(List<Ingredient> ingredients, Predicate<List<Ingredient>> isIngredientsValid) {
        if (!isIngredientsValid.test(ingredients)) {
            return 0;
        }
        return Math.max(0, ingredients.stream().mapToInt(i -> i.capacity).sum())
                * Math.max(0, ingredients.stream().mapToInt(i -> i.durability).sum())
                * Math.max(0, ingredients.stream().mapToInt(i -> i.flavor).sum())
                * Math.max(0, ingredients.stream().mapToInt(i -> i.texture).sum());
    }

    private record Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {

        public static Ingredient parse(String line) {
            String[] split1 = line.split(": ");
            int[] properties = Arrays.stream(split1[1].split(", ")).map(s -> s.split(" ")[1]).mapToInt(Integer::parseInt).toArray();
            return new Ingredient(split1[0], properties[0], properties[1], properties[2], properties[3], properties[4]);
        }

        public Ingredient multiply(int amount) {
            return new Ingredient(name, capacity * amount, durability * amount, flavor * amount, texture * amount, calories * amount);
        }
    }
}

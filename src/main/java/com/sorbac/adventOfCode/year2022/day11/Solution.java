package com.sorbac.adventOfCode.year2022.day11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {
    public static final String INPUT_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day11/input1.txt";
    public static final String TEST_FILE = "./src/main/java/com/sorbac/adventOfCode/year2022/day11/test.txt";

    public Solution() {
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE))));
        //Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE))));
        List<Monkey> monkeys = new ArrayList<>();
        while (in.hasNextLine()) {
            monkeys.add(Monkey.readMonkey(in));
            if (in.hasNextLine()) in.nextLine();
        }
        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                for (Integer item : monkey.items) {
                    int newItem = monkey.operation.apply(item) / 3;
                    int newMonkeyIdx = monkey.test.apply(newItem);
                    monkeys.get(newMonkeyIdx).items.add(newItem);
                }
                monkey.items = new ArrayList<>();
            }
        }
        Queue<Integer> maxMonkeys = new PriorityQueue<>(2, Collections.reverseOrder());
        monkeys.forEach(m -> maxMonkeys.add(m.inspection));
        System.out.println(maxMonkeys.poll() * maxMonkeys.poll());
    }

    private static class Monkey implements Comparable {
        List<Integer> items = new ArrayList<>();
        Function<Integer, Integer> operation;
        Function<Integer, Integer> test;
        int inspection = 0;

        public Monkey(List<Integer> items, Function<Integer, Integer> operation, Function<Integer, Integer> test)  {
            this.items = items;
            this.operation = operation;
            this.test = integer -> {
                inspection++;
                return test.apply(integer);
            };
        }

        public static Monkey readMonkey(Scanner in) {
            String monkeyName = in.nextLine();
            String itemsInput = in.nextLine();
            String[] itemsArr = itemsInput.substring("  Starting items: ".length()).split(", ");
            List<Integer> items = Arrays.stream(itemsArr).map(Integer::parseInt).collect(Collectors.toList());
            String operationInput = in.nextLine();
            String[] operationArr = operationInput.substring("  Operation: new = old ".length()).split(" ");
            Function<Integer, Integer> operation = createOperation(operationArr);
            String testInput = in.nextLine();
            Integer testDivNum = Integer.parseInt(testInput.substring("  Test: divisible by ".length()));
            String testResTrueInput = in.nextLine();
            Integer testResTrue = Integer.parseInt(testResTrueInput.substring("    If true: throw to monkey ".length()));
            String testResFalseInput = in.nextLine();
            Integer testResFalse = Integer.parseInt(testResFalseInput.substring("    If false: throw to monkey ".length()));
            Function<Integer, Integer> test = integer -> integer % testDivNum == 0 ? testResTrue : testResFalse;
            return new Monkey(items, operation, test);
        }

        private static Function<Integer, Integer> createOperation(String[] operationArr) {
            String op = operationArr[0];
            String operand = operationArr[1];
            return switch (op) {
                case "+" -> old -> old + (operand.equals("old") ? old : Integer.parseInt(operand));
                case "-" -> old -> old - (operand.equals("old") ? old : Integer.parseInt(operand));
                case "*" -> old -> old * (operand.equals("old") ? old : Integer.parseInt(operand));
                case "/" -> old -> old / (operand.equals("old") ? old : Integer.parseInt(operand));
                default -> throw new RuntimeException("Unexpected operand: " + op);
            };
        }

        @Override
        public int compareTo(Object o) {
            Monkey other = (Monkey) o;
            return Integer.compare(this.inspection, other.inspection);
        }
    }
}

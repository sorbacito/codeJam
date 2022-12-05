package com.sorbac.adventOfCode.year2022.day05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static final String STACKS_INPUT = "./src/main/java/com/sorbac/adventOfCode/year2022/day05/stacks.txt";
    public static final String MOVES_INPUT = "./src/main/java/com/sorbac/adventOfCode/year2022/day05/moves.txt";
    public static final int MOVE_NUMBER = 1;
    public static final int MOVE_FROM = 3;
    public static final int MOVE_TO = 5;
    private static List<Deque<Character>> stacks;
    private static boolean isModel9000 = false;

    public static void main(String[] args) throws FileNotFoundException {
        readStacks();
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(MOVES_INPUT))));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] commands = line.split(" ");
            int moveNumber = Integer.parseInt(commands[MOVE_NUMBER]);
            int moveFrom = Integer.parseInt(commands[MOVE_FROM]);
            int moveTo = Integer.parseInt(commands[MOVE_TO]);
            Deque<Character> moveFromStack = stacks.get(moveFrom - 1);
            Deque<Character> moveToStack = stacks.get(moveTo - 1);
            if (isModel9000) {
                for (int i = 0; i < moveNumber; i ++) {
                    moveToStack.add(moveFromStack.pollLast());
                }
            } else {
                List<Character> movedChars = new ArrayList<>();
                for (int i = 0; i < moveNumber; i ++) {
                    movedChars.add(moveFromStack.pollLast());
                }
                for (int i = 0; i < moveNumber; i ++) {
                    moveToStack.add(movedChars.get(movedChars.size() - i - 1));
                }
            }
        }
        StringBuilder finalState = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            finalState.append(stacks.get(i).peekLast());
        }
        System.out.println(finalState);
    }

    private static void readStacks() throws FileNotFoundException {
        stacks = new ArrayList<>(9);
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(STACKS_INPUT))));
        for (int i = 0; i < 9; i++) {
            stacks.add(new LinkedList<>());
        }
        while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int i = 0; i < 9; i++) {
                char c = line.charAt(1 + 4*i);
                if (c >= 'A' && c <= 'Z') {
                    stacks.get(i).addFirst(c);
                }
            }
        }
    }
}

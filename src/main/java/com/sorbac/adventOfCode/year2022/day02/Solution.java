package com.sorbac.adventOfCode.year2022.day02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {

    private static final String FILE_PATH = "./src/main/java/com/sorbac/adventOfCode/year2022/day02/input.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH))));
        int sum = 0;
        int sumSecond = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] round = line.split(" ");
            switch (round[0]) {
                case "A" -> {
                    switch (round[1]) {
                        case "X" -> sum += 3 + 1;
                        case "Y" -> sum += 6 + 2;
                        case "Z" -> sum += 0 + 3;
                    }
                }
                case "B" -> {
                    switch (round[1]) {
                        case "X" -> sum += 0 + 1;
                        case "Y" -> sum += 3 + 2;
                        case "Z" -> sum += 6 + 3;
                    }
                }
                case "C" -> {
                    switch (round[1]) {
                        case "X" -> sum += 6 + 1;
                        case "Y" -> sum += 0 + 2;
                        case "Z" -> sum += 3 + 3;
                    }
                }
            }
            switch (round[0]) {
                case "A" -> {
                    switch (round[1]) {
                        case "X" -> sumSecond += 0 + 3;
                        case "Y" -> sumSecond += 3 + 1;
                        case "Z" -> sumSecond += 6 + 2;
                    }
                }
                case "B" -> {
                    switch (round[1]) {
                        case "X" -> sumSecond += 0 + 1;
                        case "Y" -> sumSecond += 3 + 2;
                        case "Z" -> sumSecond += 6 + 3;
                    }
                }
                case "C" -> {
                    switch (round[1]) {
                        case "X" -> sumSecond += 0 + 2;
                        case "Y" -> sumSecond += 3 + 3;
                        case "Z" -> sumSecond += 6 + 1;
                    }
                }
            }
        }
        System.out.println(sum);
        System.out.println(sumSecond);
    }
}

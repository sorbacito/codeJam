package com.sorbac.adventOfCode.year2022.day22;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static final String INPUT_MAP = "./src/main/java/com/sorbac/adventOfCode/year2022/day22/inputTestMap.txt";
    public static final String INPUT_STEPS = "./src/main/java/com/sorbac/adventOfCode/year2022/day22/inputTestSteps.txt";

    private final List<String> lines;

    public Solution(List<String> lines) {
        this.lines = lines;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_MAP))));
        //Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(TEST_FILE))));
        List<String> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }
    }

}

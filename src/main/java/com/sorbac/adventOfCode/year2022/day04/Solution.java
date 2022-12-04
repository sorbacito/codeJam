package com.sorbac.adventOfCode.year2022.day04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2022/day04/input.txt"))));
        int sumContain = 0;
        int sumOverlap = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] pairs = line.split(",");
            String[] pair1 = pairs[0].split("-");
            String[] pair2 = pairs[1].split("-");
            int pair1_1 = Integer.parseInt(pair1[0]);
            int pair1_2 = Integer.parseInt(pair1[1]);
            int pair2_1 = Integer.parseInt(pair2[0]);
            int pair2_2 = Integer.parseInt(pair2[1]);
            if ((pair1_1 <= pair2_1 && pair1_2 >= pair2_2)
                ||(pair2_1 <= pair1_1 && pair2_2 >= pair1_2)) {
                sumContain++;
            }
            if((Math.max(pair1_2, pair2_2) - Math.min(pair1_1, pair2_1))
                - (pair1_2 - pair1_1) - (pair2_2 - pair2_1) <= 0) {
                sumOverlap++;
            }
        }
        System.out.println(sumContain);
        System.out.println(sumOverlap);
    }
}

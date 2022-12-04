package com.sorbac.adventOfCode.year2022.day01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2022/day01/input.txt"))));
        int maxSum = 0;
        int sum = 0;
        Queue<Integer> maxHeap = new PriorityQueue<>(3, Collections.reverseOrder());
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.length() > 0) {
                sum += Integer.parseInt(line);
            } else {
                maxSum = Math.max(maxSum, sum);
                maxHeap.add(sum);
                sum = 0;
            }
        }
        System.out.println(maxSum);
        System.out.println(maxHeap.poll() + maxHeap.poll() + maxHeap.poll());
    }
}

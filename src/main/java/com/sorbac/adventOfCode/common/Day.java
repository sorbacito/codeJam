package com.sorbac.adventOfCode.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day {
    public static List<String> readLines(Scanner in1) {
        List<String> lines1 = new ArrayList<>();
        while (in1.hasNextLine()) {
            lines1.add(in1.nextLine());
        }
        return lines1;
    }
}

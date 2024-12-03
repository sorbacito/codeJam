package com.sorbac.adventOfCode.year2024.day;

import com.sorbac.adventOfCode.year2024.Day2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends Day2024 {
    private static final int DAY = Integer.parseInt(Day3.class.getSimpleName().replaceFirst("Day", ""));

    protected Day3() {
        super(DAY);
    }

    protected Day3(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day3().printParts(161289189, 83595109);
        new Day3("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
                .printPart1(161);
        new Day3("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")
                .printPart2(48);
    }

    @Override
    protected Object part1() {
        String input = day();
        return sumMulsInString(input);
    }

    @Override
    protected Object part2() {
        String input = day();
        List<String> donts = new ArrayList<>(Arrays.asList(input.split("don't\\(\\)")));
        List<String> dos = new ArrayList<>();
        dos.add(donts.getFirst());
        donts.removeFirst();
        for (String dont : donts) {
            List<String> list = new ArrayList<>(Arrays.asList(dont.split("do\\(\\)")));
            list.removeFirst();
            dos.addAll(list);
        }
        return dos.stream().mapToInt(Day3::sumMulsInString).sum();
    }

    private static int sumMulsInString(String input) {
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);
        int sum = 0;
        while (matcher.find()) {
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));

            sum += num1 * num2;
        }
        return sum;
    }
}

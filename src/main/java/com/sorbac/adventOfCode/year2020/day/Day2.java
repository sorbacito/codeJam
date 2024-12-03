package com.sorbac.adventOfCode.year2020.day;

import com.sorbac.adventOfCode.year2020.Day2020;

public class Day2 extends Day2020 {
    private static final int DAY = Integer.parseInt(Day2.class.getSimpleName().replaceFirst("Day", ""));

    protected Day2() {
        super(DAY);
    }

    protected Day2(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day2().printParts();
        new Day2("1-3 a: abcde\n" +
                "1-3 b: cdefg\n" +
                "2-9 c: ccccccccc").printParts(2L, 1L);
//        new Day2("test input 2").printPart1("expected result 2");
    }

    @Override
    protected Object part1() {
        return dayStreamLines().map(Day2::createPasswordPolicy
        ).filter(PasswordPolicy::isValidPolicy1).count();
    }

    @Override
    protected Object part2() {
        return dayStreamLines().map(Day2::createPasswordPolicy
        ).filter(PasswordPolicy::isValidPolicy2).count();
    }

    private static PasswordPolicy createPasswordPolicy(String line) {
        String[] parts = line.split(": ");
        String[] policyParts = parts[0].split(" ");
        String[] minMax = policyParts[0].split("-");
        return new PasswordPolicy(Integer.parseInt(minMax[0]), Integer.parseInt(minMax[1]), policyParts[1].charAt(0), parts[1]);
    }

    private static class PasswordPolicy {
        private final int firstValue;
        private final int secondValue;
        private final char myLetter;
        private final String myPassword;

        public PasswordPolicy(int theMin, int theMax, char theLetter, String thePassword) {
            firstValue = theMin;
            secondValue = theMax;
            myLetter = theLetter;
            myPassword = thePassword;
        }

        public boolean isValidPolicy1() {
            long count = myPassword.chars().filter(ch -> ch == myLetter).count();
            return count >= firstValue && count <= secondValue;
        }

        public boolean isValidPolicy2() {
            return myPassword.charAt(firstValue - 1) == myLetter ^ myPassword.charAt(secondValue - 1) == myLetter;
        }
    }
}

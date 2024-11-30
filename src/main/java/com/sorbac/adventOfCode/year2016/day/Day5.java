package com.sorbac.adventOfCode.year2016.day;


import com.sorbac.adventOfCode.common.Pair;
import com.sorbac.adventOfCode.year2016.Day2016;
import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.function.Function;

public class Day5 extends Day2016 {
    private static final int DAY = Integer.parseInt(Day5.class.getSimpleName().replaceFirst("Day", ""));
    public static final int DRAMATIC_DELAY_MILLIS = 0;
//    public static final int DRAMATIC_DELAY_MILLIS = 1000;

    protected Day5() {
        super(DAY);
    }

    protected Day5(String input) {
        super(DAY, input);
    }

    @SneakyThrows
    public static void main(String[] args) {
//        new Day5("abc").printPart1("18f47a30");

        new Day5("abbhdwsy").printPart2(/*"801b56a7", */"424a0197");
    }

    @Override
    @SneakyThrows
    protected Object part1() {
        return solvePart(new Part1Function());
    }

    @Override
    @SneakyThrows
    protected Object part2() {
        return solvePart((hash) -> new Pair<>(hash.charAt(5) - '0', hash.charAt(6)));
    }

    private static class Part1Function implements Function<String, Pair<Integer, Character>> {
        private int i = 0;

        @Override
        public Pair<Integer, Character> apply(String s) {
            return new Pair<>(i++, s.charAt(5));
        }
    }

    @SneakyThrows
    private String solvePart(Function<String, Pair<Integer, Character>> f) throws NoSuchAlgorithmException {
        char[] password = "--------".toCharArray();
        int foundDigits = 0;
        long i = 0;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        while (foundDigits < 8) {
            String md5input = day() + i++;
            md5.update(md5input.getBytes());
            byte[] digest = md5.digest();
            String myHash = HexFormat.of().formatHex(digest);
            if (myHash.startsWith("00000")) {
                Pair<Integer, Character> passwordMapper = f.apply(myHash);
                if (passwordMapper.left() < 8 && password[passwordMapper.left()] == '-') {
                    foundDigits++;
                    password[passwordMapper.left()] = passwordMapper.right();
                    System.out.print("\r" + new String(password));
                    Thread.sleep(DRAMATIC_DELAY_MILLIS);
                }
            }
        }

        System.out.println();
        return new String(password);
    }
}

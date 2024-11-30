package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;
import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Day4 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day4.class.getSimpleName().replaceFirst("Day", ""));

    protected Day4() {
        super(DAY);
    }

    protected Day4(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day4("abcdef").printParts(609043);
        new Day4("pqrstuv").printParts(1048970);

        System.out.println("Solution:");
        new Day4("ckczppom").printParts();
    }

    @Override
    @SneakyThrows
    protected Object part1() {
        return getAnswer("00000");
    }

    private int getAnswer(String isAnswerPrefix) throws NoSuchAlgorithmException {
        int answer = 0;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        while (true) {
            String md5input = day() + answer++;
            md5.update(md5input.getBytes());
            byte[] digest = md5.digest();
            String myHash = HexFormat.of().formatHex(digest);
            if (myHash.startsWith(isAnswerPrefix)) {
                return answer - 1;
            }
        }
    }

    @Override
    @SneakyThrows
    protected Object part2() {
        return getAnswer("000000");
    }
}

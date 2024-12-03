package com.sorbac.adventOfCode.year2016.day;


import com.sorbac.adventOfCode.year2016.Day2016;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day4 extends Day2016 {
    private static final int DAY = Integer.parseInt(Day4.class.getSimpleName().replaceFirst("Day", ""));

    protected Day4() {
        super(DAY);
    }

    protected Day4(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day4(String.join(DEFAULT_DELIMITER,
                "aaaaa-bbb-z-y-x-123[abxyz]",
                "a-b-c-d-e-f-g-h-987[abcde]",
                "not-a-real-room-404[oarel]",
                "totally-real-room-200[decoy]"
        )).printPart1(1514);

        new Day4().printParts(173787);
    }

    @Override
    protected Object part1() {
        return dayStreamLines().map(Room::parse).filter(Room::isReal).mapToInt(Room::getMySectorId).sum();
    }

    @Override
    protected Object part2() {
        return dayStreamLines().map(Room::parse).filter(Room::isReal)
                .filter(room -> room.shiftCypher().contains("northpole object storage"))
                .mapToInt(Room::getMySectorId).findFirst().orElseThrow();
    }

    @Data
    private static class Room {
        private final String myName;
        private final int mySectorId;
        private final String myChecksum;

        public static Room parse(String aLine) {
            String[] myParts = aLine.split("-");
            String myName = String.join(" ", List.of(myParts).subList(0, myParts.length - 1));
            int mySectorId = Integer.parseInt(myParts[myParts.length - 1].split("\\[")[0]);
            String myChecksum = myParts[myParts.length - 1].split("\\[")[1].replace("]", "");
            return new Room(myName, mySectorId, myChecksum);
        }

        public boolean isReal() {
            Map<Character, Long> charsCount = myName.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> c != ' ')
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            String producedSum = charsCount.entrySet().stream().sorted(Map.Entry.<Character, Long>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
                    .limit(5).map(Map.Entry::getKey).map(Object::toString).collect(Collectors.joining());
            return producedSum.equals(myChecksum);
        }

        public String shiftCypher() {
            return myName.chars().mapToObj(c -> (char) c).map(c -> {
                if (c == ' ') {
                    return ' ';
                }
                return (char) ('a' + (c - 'a' + mySectorId) % 26);
            }).map(Object::toString).collect(Collectors.joining());
        }
    }
}

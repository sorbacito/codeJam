package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.common.Pair;
import com.sorbac.adventOfCode.year2015.Day2015;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day14.class.getSimpleName().replaceFirst("Day", ""));

    protected Day14() {
        super(DAY);
    }

    protected Day14(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        String comet = "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.";
        String dancer = "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.";
//        new Day14(comet).setArgument(1000).printPart1(1120);
//        new Day14(dancer).setArgument(1000).printPart1(1056);
        new Day14(String.join("\n", comet, dancer)).setArgument(1000).printParts(1120, 689);

        System.out.println("\nSolution:");
        new Day14().setArgument(2503).printParts(2655, 1059);
    }

    @Override
    protected Object part1() {
        int time = (int) getArgument();
        return dayStream().map(Reindeer::of).mapToInt(r -> r.traveledKm(time)).max().orElse(-1);
    }

    @Override
    protected Object part2() {
        int time = (int) getArgument();
        List<Reindeer> reindeers = dayStream().map(Reindeer::of).toList();
        Map<String, Integer> reinderPoints = reindeers.stream().collect(Collectors.toMap(Reindeer::name, r -> 0));
        for (int i = 1; i <= time; i++) {
            int currentTime = i;
            List<Pair<Reindeer, Integer>> currentTimes = reindeers.stream().map(r -> new Pair<>(r, r.traveledKm(currentTime))).toList();
            Pair<Reindeer, Integer> curMax = currentTimes.stream()
                    .max(Comparator.comparingInt(Pair::right)).orElse(null);
            currentTimes.stream()
                    .filter(p -> p.right().equals(curMax.right()))
                    .forEach(p -> reinderPoints.put(p.left().name(), reinderPoints.get(p.left().name()) + 1));
        }
        return reinderPoints.values().stream().max(Integer::compareTo).orElse(-1);
    }

    private record Reindeer(String name, int speed, int flyTime, int restTime) {

        public static Reindeer of(String reinder) {
            String[] split1 = reinder.split(", but then must rest for ");
            int restTime = Integer.parseInt(split1[1].replace(" seconds.", ""));
            String[] split2 = split1[0].split(" can fly ");
            String name = split2[0];
            String[] split3 = split2[1].split(" km/s for ");
            int speed = Integer.parseInt(split3[0]);
            int flyTime = Integer.parseInt(split3[1].replace(" seconds", ""));
            return new Reindeer(name, speed, flyTime, restTime);
        }

        public int traveledKm(int time) {
            int cycleTime = flyTime + restTime;
            int cycles = time / cycleTime;
            int remainingTime = time % cycleTime;
            return cycles * flyTime * speed + Math.min(remainingTime, flyTime) * speed;
        }
    }
}

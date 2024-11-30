package com.sorbac.adventOfCode.year2023.day22;

import com.sorbac.adventOfCode.common.Day;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner inTest1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day22/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day22/test11.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day22/input1.txt"))));
        Scanner inTest2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day22/test2.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day22/test22.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day22/input2.txt"))));

        System.out.println("Part 1");
        List<String> linesTest1 = Day.readLines(inTest1);
        long part1TestAnswer = getPart1Answer(linesTest1);
        System.out.println("Test: " + part1TestAnswer + (part1TestAnswer == 530 ? " OK" : " NOT OK"));

        List<String> lines1 = Day.readLines(in1);
        long part1Answer = getPart1Answer(lines1);
        System.out.println("Problem: " + part1Answer);

        System.out.println();
        System.out.println("Part 2");
        List<String> linesTest2 = Day.readLines(inTest2);
        long part2TestAnswer = getPart2Answer(linesTest2);
        System.out.println("Test: " + part2TestAnswer + (part1TestAnswer == 7 ? " OK" : " NOT OK"));

        List<String> lines2 = Day.readLines(in2);
        long part2Answer = getPart2Answer(lines2);
        System.out.println("Problem: " + part2Answer);
        // too high 120091
        // too high 137181
        // not correct 2355
        // not correct 2720
        // not correct 82555
        // not correct 82042
        // not correct 43814
    }

    private static long getPart1Answer(List<String> lines) {
        List<Pair> fallenCubes = getFallenCubes(lines);

        List<Pair> canBeDisintegrated = getCanBeDisintegrated(fallenCubes);
        return canBeDisintegrated.size();
    }

    private static List<Pair> getCanBeDisintegrated(List<Pair> fallenCubes) {
        return fallenCubes.stream()
                .filter(cube -> fallenCubes.stream()
                        .filter(c -> c.isRightAbove(cube) && cube.isRightBelow(c))
                        .mapToInt(above -> {
                            List<Pair> belows = fallenCubes.stream()
                                    .filter(c -> c.isRightBelow(above) && above.isRightAbove(c)).toList();
                            List<Pair> belowAbove = new ArrayList<>(belows.stream().filter(below ->
                                    below.isOverlapOf(above) && above.isOverlapOf(below)).toList());
                            belowAbove.remove(cube);
                            return belowAbove.size();
                        })
                        .filter(size -> size == 0)
                        .findAny()
                        .isEmpty()
                ).toList();
    }

    private static long getPart2Answer(List<String> lines) {
        List<Pair> fallenCubes = getFallenCubes(lines);

        Map<Pair, Set<Pair>> supportOf = new HashMap<>();
        Map<Pair, Set<Pair>> supportedBy = new HashMap<>();
        fallenCubes.forEach(cube -> supportOf.put(cube, cube.supportOf(fallenCubes)));
        fallenCubes.forEach(cube -> supportedBy.put(cube, cube.supportedBy(fallenCubes)));

        fallenCubes.removeAll(getCanBeDisintegrated(fallenCubes));
        List<Long> values = fallenCubes.stream()
                .mapToLong(cube -> dropCount(cube, supportOf, supportedBy))
                .boxed()
                .toList();
        return values.stream().mapToLong(Long::longValue).sum();
    }

    private static long dropCount(Pair cube, Map<Pair, Set<Pair>> supportOf, Map<Pair, Set<Pair>> supportedBy) {
        long dropped = 0;
        Set<Pair> cur = new HashSet<>();
        cur.add(cube);
        while (!cur.isEmpty()) {
            Set<Pair> curDrop = cur;
            Set<Pair> potentialDrops = curDrop.stream()
                    .map(supportOf::get)
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());
            Set<Pair> actualDrops = potentialDrops.stream()
                    .filter(c -> curDrop.containsAll(supportedBy.get(c))).collect(Collectors.toSet());
            dropped += actualDrops.size();
            cur = actualDrops;
        }
        return dropped;
    }

    private static Set<Pair> fallCount(Pair cube, List<Pair> fallenCubes, Map<Pair, Set<Pair>> cache) {
        if (cache.containsKey(cube)) {
            return cache.get(cube);
        }
        Set<Pair> toFall = cube.supportOf(fallenCubes).stream()
                .map(above -> fallCount(above, fallenCubes, cache))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
        toFall.add(cube);
        cache.put(cube, toFall);
        return toFall;
    }

    private static List<Pair> getFallenCubes(List<String> lines) {
        List<Pair> cubes = new ArrayList<>(lines.stream().map(line -> line.split("~"))
                .map(s3d -> {
                    String[] cube1Split = s3d[0].split(",");
                    String[] cube2Split = s3d[1].split(",");
                    return new Pair(new Loc3D(Integer.parseInt(cube1Split[0]), Integer.parseInt(cube1Split[1]), Integer.parseInt(cube1Split[2])),
                            new Loc3D(Integer.parseInt(cube2Split[0]), Integer.parseInt(cube2Split[1]), Integer.parseInt(cube2Split[2])));
                }).toList());
        cubes.sort(Comparator.comparingInt((Pair p) -> p.start().z())
                .thenComparingInt(p -> p.start().y())
                .thenComparingInt(p -> p.start().x()));
        List<Pair> fallenCubes = new ArrayList<>();
        cubes.forEach(cube -> fallenCubes.add(cube.fallCube(fallenCubes)));
        return fallenCubes;
    }

    private static record Loc3D(int x, int y, int z) {
    }

    private record Pair(Loc3D start, Loc3D end) {
        public Pair fallCube(List<Pair> cubes) {
            int minZ = cubes.stream().filter(this::isOverlapOf)
                    .mapToInt(cube -> cube.end.z).max().orElse(0);
            return new Pair(new Loc3D(this.start.x, this.start.y, minZ + 1),
                    new Loc3D(this.end.x, this.end.y, minZ + 1 + this.end.z - this.start.z));
        }

        public Set<Pair> supportOf(List<Pair> cubes) {
            return cubes.stream().filter(cube -> this.isOverlapOf(cube) && this.isRightBelow(cube))
                    .collect(Collectors.toSet());
        }

        public Set<Pair> supportedBy(List<Pair> cubes) {
            return cubes.stream().filter(cube -> this.isOverlapOf(cube) && this.isRightAbove(cube))
                    .collect(Collectors.toSet());
        }

        private boolean isRightAbove(Pair cube) {
            return this.start.z - 1 == cube.end.z ;
        }

        private boolean isRightBelow(Pair cube) {
            return this.end.z + 1 == cube.start.z;
        }

        public boolean isOverlapOf(Pair cube) {
            return isOverlap(cube.start.x, cube.end.x, this.start.x, this.end.x)
                    && isOverlap(cube.start.y, cube.end.y, this.start.y, this.end.y);
        }

    }
    private static boolean isOverlap(int c1s, int c1e, int c2s, int c2e) {
        return c1s <= c2e && c2s <= c1e;
    }

}

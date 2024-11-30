package com.sorbac.adventOfCode.year2023.day22;

import com.sorbac.adventOfCode.common.Day;
import com.sorbac.adventOfCode.common.Loc3D;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static final Loc3D DOWN_BY_1 = new Loc3D(0, 0, -1);

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
        System.out.println("Test: " + part1TestAnswer + (part1TestAnswer == 5 ? " OK" : " NOT OK"));

        List<String> lines1 = Day.readLines(in1);
        long part1Answer = getPart1Answer(lines1);
        System.out.println("Problem: " + part1Answer);

        System.out.println();
        System.out.println("Part 2");
        List<String> linesTest2 = Day.readLines(inTest2);
        long part2TestAnswer = getPart2Answer(linesTest2);
        System.out.println("Test: " + part2TestAnswer + (part2TestAnswer == 7 ? " OK" : " NOT OK"));

        List<String> lines2 = Day.readLines(in2);
        long part2Answer = getPart2Answer(lines2);
        System.out.println("Problem: " + part2Answer + (part2Answer == 93292 ? " OK" : " NOT OK")); //93292
        // too high 120091
        // too high 137181
        // not correct 2355
        // not correct 2720
        // not correct 82555
        // not correct 82042
        // not correct 43814
        // not correct 82042
    }

    private static long getPart1Answer(List<String> lines) {
        Set<Cube> fallenCubes = getFallenCubes(lines);

        Set<Cube> canBeDisintegrated = getCanBeDisintegrated(fallenCubes);
        return canBeDisintegrated.size();
    }

    private static Set<Cube> getCanBeDisintegrated(Set<Cube> fallenCubes) {
        return fallenCubes.stream()
                .filter(cube -> fallenCubes.stream()
                        .filter(c -> c.isRightAbove(cube) && cube.isRightBelow(c))
                        .mapToInt(above -> {
                            List<Cube> belows = fallenCubes.stream()
                                    .filter(c -> c.isRightBelow(above) && above.isRightAbove(c)).toList();
                            List<Cube> belowAbove = new ArrayList<>(belows.stream().filter(below ->
                                    below.isOverlapOf(above) && above.isOverlapOf(below)).toList());
                            belowAbove.remove(cube);
                            return belowAbove.size();
                        })
                        .filter(size -> size == 0)
                        .findAny()
                        .isEmpty()
                ).collect(Collectors.toSet());
    }

    private static long getPart2Answer(List<String> lines) {
        Set<Cube> fallenCubes = getFallenCubes(lines);

        Map<Cube, Set<Cube>> supportOf = new HashMap<>();
        Map<Cube, Set<Cube>> supportedBy = new HashMap<>();
        fallenCubes.forEach(cube -> supportOf.put(cube, cube.supportOf(fallenCubes)));
        fallenCubes.forEach(cube -> supportedBy.put(cube, cube.supportedBy(fallenCubes)));

        fallenCubes.removeAll(getCanBeDisintegrated(fallenCubes));
        List<Long> values = fallenCubes.stream()
                .mapToLong(cube -> dropCount(cube, supportOf, supportedBy))
                .boxed()
                .toList();
        return values.stream().mapToLong(Long::longValue).sum();
    }

    private static long dropCount(Cube cube, Map<Cube, Set<Cube>> supportOf, Map<Cube, Set<Cube>> supportedBy) {
        long dropped = 0;
        Set<Cube> cur = new HashSet<>();
        cur.add(cube);
        while (!cur.isEmpty()) {
            Set<Cube> curDrop = cur;
            Set<Cube> potentialDrops = curDrop.stream()
                    .map(supportOf::get)
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());
            Set<Cube> actualDrops = potentialDrops.stream()
                    .filter(c -> curDrop.containsAll(supportedBy.get(c))).collect(Collectors.toSet());
            dropped += actualDrops.size();
            cur = actualDrops;
        }
        return dropped;
    }

    private static Set<Cube> fallCount(Cube cube, Set<Cube> fallenCubes, Map<Cube, Set<Cube>> cache) {
        if (cache.containsKey(cube)) {
            return cache.get(cube);
        }
        Set<Cube> toFall = cube.supportOf(fallenCubes).stream()
                .map(above -> fallCount(above, fallenCubes, cache))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
        toFall.add(cube);
        cache.put(cube, toFall);
        return toFall;
    }

    private static Set<Cube> getFallenCubes(List<String> lines) {
        Set<Cube> cubes = readInput(lines);
        return dropCubes(cubes);
    }

    private static Set<Cube> dropCubes(Set<Cube> initCubes) {
        Set<Cube> cubes = new HashSet<>();
        Set<Cube> newCubes = new HashSet<>(initCubes);
        while (!cubes.equals(newCubes)) {
            Set<Cube> curCubes = new HashSet<>(newCubes);
            newCubes = curCubes.stream()
                    .map(cube -> cube.start.z() == 1 || curCubes.stream().anyMatch(c -> c.isRightBelow(cube) && c.isOverlapOf(cube))
                            ? cube : cube.move(DOWN_BY_1))
                    .collect(Collectors.toSet());
            cubes = curCubes;
        }

        return cubes;
    }

    private static Set<Cube> readInput(List<String> lines) {
        return lines.stream().map(line -> line.split("~"))
                .map(s3d -> {
                    String[] cube1Split = s3d[0].split(",");
                    String[] cube2Split = s3d[1].split(",");
                    return new Cube(new Loc3D(Integer.parseInt(cube1Split[0]), Integer.parseInt(cube1Split[1]), Integer.parseInt(cube1Split[2])),
                            new Loc3D(Integer.parseInt(cube2Split[0]), Integer.parseInt(cube2Split[1]), Integer.parseInt(cube2Split[2])));
                }).collect(Collectors.toSet());
    }

    private record Cube(Loc3D start, Loc3D end) {
//        public Cube fallCube(List<Cube> cubes) {
//            int minZ = cubes.stream().filter(this::isOverlapOf)
//                    .mapToInt(cube -> cube.end.z).max().orElse(0);
//            return new Cube(new Loc3D(this.start.x, this.start.y, minZ + 1),
//                    new Loc3D(this.end.x, this.end.y, minZ + 1 + this.end.z - this.start.z));
//        }

        public Set<Cube> supportOf(Set<Cube> cubes) {
            return cubes.stream().filter(cube -> this.isOverlapOf(cube) && this.isRightBelow(cube))
                    .collect(Collectors.toSet());
        }

        public Set<Cube> supportedBy(Set<Cube> cubes) {
            return cubes.stream().filter(cube -> this.isOverlapOf(cube) && this.isRightAbove(cube))
                    .collect(Collectors.toSet());
        }

        private boolean isRightAbove(Cube cube) {
            return this.start.z() - 1 == cube.end.z();
        }

        private boolean isRightBelow(Cube cube) {
            return this.end.z() + 1 == cube.start.z();
        }

        public boolean isOverlapOf(Cube cube) {
            return isOverlap(cube.start.x(), cube.end.x(), this.start.x(), this.end.x())
                    && isOverlap(cube.start.y(), cube.end.y(), this.start.y(), this.end.y());
        }

        public Cube move(Loc3D dif) {
            return new Cube(this.start.move(dif), this.end.move(dif));
        }

    }

    private static boolean isOverlap(long c1s, long c1e, long c2s, long c2e) {
        return c1s <= c2e && c2s <= c1e;
    }

}

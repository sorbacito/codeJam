package com.sorbac.adventOfCode.year2023.day23;

import com.sorbac.adventOfCode.common.Day;
import com.sorbac.adventOfCode.common.Direction;
import com.sorbac.adventOfCode.common.Loc;
import com.sorbac.adventOfCode.common.Pair;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner inTest1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day23/test1.txt"))));
//        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day23/test11.txt"))));
        Scanner in1 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day23/input1.txt"))));
        Scanner inTest2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day23/test2.txt"))));
//        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day23/test22.txt"))));
        Scanner in2 = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/java/com/sorbac/adventOfCode/year2023/day23/input2.txt"))));

        System.out.println("Part 1");
        List<String> linesTest1 = Day.readLines(inTest1);
        long part1TestAnswer = getPart1Answer(linesTest1);
        System.out.println("Test: " + part1TestAnswer + (part1TestAnswer == 94 ? " OK" : " NOT OK"));

        List<String> lines1 = Day.readLines(in1);
        long part1Answer = getPart1Answer(lines1);
        System.out.println("Problem: " + part1Answer + (part1Answer == 2086 ? " OK" : " NOT OK"));

        System.out.println();
        System.out.println("Part 2");
        List<String> linesTest2 = Day.readLines(inTest2);
        long part2TestAnswer = getPart2Answer(linesTest2);
        System.out.println("Test: " + part2TestAnswer + (part2TestAnswer == 154 ? " OK" : " NOT OK"));

        List<String> lines2 = Day.readLines(in2);
        long part2Answer = getPart2Answer(lines2);
        System.out.println("Problem: " + part2Answer + (part2Answer == 6526 ? " OK" : " NOT OK"));
    }

    private static long getPart1Answer(List<String> lines) {
        return getMaxPath(lines, Solution::isValidPart1);
    }

    private static long getMaxPath(List<String> lines, BiFunction<Pair<Direction, Loc>, List<String>, Boolean> isValid) {
        long max = 0;
        Loc start = new Loc(IntStream.range(0, lines.size()).filter(i -> lines.getFirst().charAt(i) == '.').findFirst().orElse(-1), 0);
        Loc end = new Loc(IntStream.range(0, lines.size()).filter(i -> lines.getLast().charAt(i) == '.').findFirst().orElse(-1), lines.size() - 1);
        Pair<Set<Loc>, Loc> paths = new Pair<>(Set.of(), start);
        Deque<Pair<Set<Loc>, Loc>> pathsToVisit = new LinkedList<>();
        pathsToVisit.add(paths);

        while (!pathsToVisit.isEmpty()) {
            Pair<Set<Loc>, Loc> path = pathsToVisit.poll();
            if (path.right().equals(end)) {
                long oldMax = max;
                max = Math.max(max, path.left().size());
                if (oldMax != max) {
                    System.out.println(max);
                }
            }
            Arrays.stream(Direction.values())
                    .map(direction -> new Pair<>(direction, path.right().move(direction)))
                    .filter(pair -> isValid.apply(pair, lines) && !path.left().contains(pair.right()))
                    .forEach(pair -> pathsToVisit.addFirst(new Pair<>(new HashSet<>(path.left()) {{
                        add(path.right());
                    }}, pair.right())));
        }
        return max;
    }

    private static boolean isValidPart1(Pair<Direction, Loc> pair, List<String> lines) {
        Loc loc = pair.right();
        Direction dir = pair.left();
        if (loc.row() < 0 || loc.row() >= lines.size() || loc.col() < 0 || loc.col() >= lines.getFirst().length()) {
            return false;
        }
        char c = lines.get((int) loc.row()).charAt((int) loc.col());
        return (c == '>' && dir == Direction.RIGHT)
                || (c == '<' && dir == Direction.LEFT)
                || (c == '^' && dir == Direction.UP)
                || (c == 'v' && dir == Direction.DOWN)
                || c == '.';
    }

    private static Predicate<Loc> isValidPart2(List<String> lines) {
        return loc -> {
            if (loc.row() < 0 || loc.row() >= lines.size() || loc.col() < 0 || loc.col() >= lines.getFirst().length()) {
                return false;
            }
            return lines.get((int) loc.row()).charAt((int) loc.col()) != '#';
        };
    }

    private static WeightedMultigraph<Loc, DefaultWeightedEdge> constructGraph(Loc start, Predicate<Loc> isFree) {
        WeightedMultigraph<Loc, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        graph.addVertex(start);

        Queue<List<Loc>> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(List.of(start));
        while (!nodesToVisit.isEmpty()) {
            List<Loc> path = nodesToVisit.poll();
            List<Loc> nextNodePath = getPathToNextNode(path, isFree);
            Loc nextNode = nextNodePath.getLast();
            if (!graph.containsVertex(nextNode)) {
                graph.addVertex(nextNode);
                getAccessibleLocs(nextNode, isFree).stream()
                        .filter(loc -> !nextNodePath.contains(loc))
                        .forEach(loc -> nodesToVisit.add(List.of(nextNode, loc)));
            }
            if (nextNodePath.size() > 1) {
                DefaultWeightedEdge edge = graph.addEdge(nextNodePath.getFirst(), nextNodePath.getLast());
                graph.setEdgeWeight(edge, nextNodePath.size() - 1);
            }
        }
        return graph;
    }

    private static List<Loc> getPathToNextNode(List<Loc> path, Predicate<Loc> isFree) {
        List<Loc> curPath = new ArrayList<>(path);
        Set<Loc> nodes;
        while (true) {
            Loc node = curPath.getLast();
            nodes = getAccessibleLocs(node, isFree).stream()
                    .filter(loc -> !curPath.contains(loc)).collect(Collectors.toSet());
            if (nodes.size() != 1) {
                break;
            } else {
                curPath.add(nodes.iterator().next());
            }
        }
        return curPath;
    }

    private static Set<Loc> getAccessibleLocs(Loc node, Predicate<Loc> isFree) {
        return Arrays.stream(Direction.values())
                .map(node::move)
                .filter(isFree)
                .collect(Collectors.toSet());
    }

    private static Set<Pair<Loc, Direction>> getNextSteps(Pair<Loc, Direction> node, Predicate<Loc> isFree) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction != node.right().turnBack())
                .filter(direction -> isFree.test(node.left()))
                .map(direction -> new Pair<>(node.left().move(direction), direction))
                .collect(Collectors.toSet());
    }

    private static long getPart2Answer(List<String> lines) {
        Loc start = new Loc(IntStream.range(0, lines.size()).filter(i -> lines.getFirst().charAt(i) == '.').findFirst().orElse(-1), 0);
        Loc end = new Loc(IntStream.range(0, lines.size()).filter(i -> lines.getLast().charAt(i) == '.').findFirst().orElse(-1), lines.size() - 1);
        WeightedMultigraph<Loc, DefaultWeightedEdge> graph = constructGraph(start, isValidPart2(lines));
        List<Loc> locs = longestPath(graph, start, end);
        return (long) sumEdgesWeight(graph, getPathEdges(graph, locs));
    }

    private static List<Loc> longestPath(Graph<Loc, DefaultWeightedEdge> graph, Loc start, Loc end) {
        List<Loc> longestPath = List.of();
        double max = 0;
        Deque<List<Loc>> pathsToVisit = new LinkedList<>();
        pathsToVisit.addFirst(List.of(start));
        while (!pathsToVisit.isEmpty()) {
            List<Loc> path = pathsToVisit.poll();
            if (path.getLast().equals(end)) {
                List<DefaultWeightedEdge> edges = getPathEdges(graph, path);
                double pathLength = sumEdgesWeight(graph, edges);
                if (pathLength > max) {
                    longestPath = path;
                    max = pathLength;
                }
            }
            Set<Loc> adjacentVertices = graph.edgesOf(path.getLast())
                    .stream().map(graph::getEdgeTarget)
                    .filter(loc -> !path.contains(loc))
                    .collect(Collectors.toSet());
            adjacentVertices.addAll(graph.edgesOf(path.getLast())
                    .stream().map(graph::getEdgeSource)
                    .filter(loc -> !path.contains(loc))
                    .collect(Collectors.toSet()));
            adjacentVertices.forEach(loc -> pathsToVisit.addFirst(new ArrayList<>(path) {{
                add(loc);
            }}));
        }
        return longestPath;
    }

    private static double sumEdgesWeight(Graph<Loc, DefaultWeightedEdge> graph, List<DefaultWeightedEdge> edges) {
        return edges.stream().mapToDouble(graph::getEdgeWeight).sum();
    }

    private static List<DefaultWeightedEdge> getPathEdges(Graph<Loc, DefaultWeightedEdge> graph, List<Loc> path) {
        return IntStream.range(0, path.size() - 1)
                .mapToObj(i -> graph.getEdge(path.get(i), path.get(i + 1)))
                .toList();
    }
}

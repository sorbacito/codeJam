package com.sorbac.adventOfCode.year2015.day;

import com.sorbac.adventOfCode.year2015.Day2015;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day9 extends Day2015 {
    private static final int DAY = Integer.parseInt(Day9.class.getSimpleName().replaceFirst("Day", ""));

    protected Day9() {
        super(DAY);
    }

    protected Day9(String input) {
        super(DAY, input);
    }

    public static void main(String[] args) {
        new Day9().setExample(1).printParts(605, 982);
        new Day9().printParts(117, 909);
    }

    @Override
    protected Object part1() {
        Graph<String, DefaultWeightedEdge> graph = loadGraph();

        int minPath = Integer.MAX_VALUE;
        AllDirectedPaths<String, DefaultWeightedEdge> allPathsFinder = new AllDirectedPaths<>(graph);
        ArrayList<String> vertices = new ArrayList<>(graph.vertexSet());
        for (int first = 0; first < vertices.size(); first++) {
            for (int second = first + 1; second < vertices.size(); second++) {
                List<GraphPath<String, DefaultWeightedEdge>> allPaths = allPathsFinder.getAllPaths(vertices.get(first), vertices.get(second), true, graph.vertexSet().size());
                Double minCurPath = allPaths.stream().filter(path -> new HashSet<>(path.getVertexList()).containsAll(vertices)).map(GraphPath::getWeight).min(Double::compareTo).orElse(Double.MAX_VALUE);
                if (minCurPath < minPath) {
                    minPath = minCurPath.intValue();
                }
            }
        }

        return minPath;
    }

    private Graph<String, DefaultWeightedEdge> loadGraph() {
        Graph<String, DefaultWeightedEdge> graph = GraphTypeBuilder
                .<String, DefaultWeightedEdge>directed()
                .allowingMultipleEdges(false)
                .allowingSelfLoops(false)
                .edgeSupplier(DefaultWeightedEdge::new)
                .weighted(true).buildGraph();
        dayStream().forEach(s -> {
            String[] edgeWeight = s.split(" = ");
            String[] vertices = edgeWeight[0].split(" to ");
            Arrays.stream(vertices).forEach(graph::addVertex);
            DefaultWeightedEdge edge = graph.addEdge(vertices[0], vertices[1]);
            graph.setEdgeWeight(edge, Integer.parseInt(edgeWeight[1]));
            DefaultWeightedEdge edge1 = graph.addEdge(vertices[1], vertices[0]);
            graph.setEdgeWeight(edge1, Integer.parseInt(edgeWeight[1]));
        });
        return graph;
    }

    @Override
    protected Object part2() {
        Graph<String, DefaultWeightedEdge> graph = loadGraph();

        int maxPath = -1;
        AllDirectedPaths<String, DefaultWeightedEdge> allPathsFinder = new AllDirectedPaths<>(graph);
        ArrayList<String> vertices = new ArrayList<>(graph.vertexSet());
        for (int first = 0; first < vertices.size(); first++) {
            for (int second = first + 1; second < vertices.size(); second++) {
                List<GraphPath<String, DefaultWeightedEdge>> allPaths = allPathsFinder.getAllPaths(vertices.get(first), vertices.get(second), true, graph.vertexSet().size());
                Double maxCurPath = allPaths.stream().filter(path -> new HashSet<>(path.getVertexList()).containsAll(vertices)).map(GraphPath::getWeight).max(Double::compareTo).orElse(-1d);
                if (maxCurPath > maxPath) {
                    maxPath = maxCurPath.intValue();
                }
            }
        }

        return maxPath;
    }
}

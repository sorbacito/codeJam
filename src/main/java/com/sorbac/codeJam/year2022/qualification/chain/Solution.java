package com.sorbac.codeJam.year2022.qualification.chain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            Chain chain = readChain(in);
            System.out.println("Case #" + i + ": " + maxFun(chain));
        }
    }

    private static long maxFun(Chain chain) {
        long fun = 0;
        Deque<Integer> stack = new ArrayDeque<>(chain.holes);
        while (!stack.isEmpty()) {
            Integer nodeId = stack.pop();
            ChainNode node = chain.nodes[nodeId];
            if (!node.visited) {
                if (node.childs.size() > 0) {
                    stack.addFirst(nodeId);
                    node.childs.forEach(stack::addFirst);
                } else {
                    node.maxValue = node.value;
                    node.processed = true;
                    if (chain.holes.contains(nodeId)) {
                        fun += node.maxValue;
                    }
                }
                node.visited = true;
            } else if (!node.processed) {
                if (node.childs.size() == 1) {
                    node.maxValue = Math.max(node.value, chain.nodes[node.childs.get(0)].maxValue);
                } else {
                    long sum = 0;
                    int min = Integer.MAX_VALUE;
                    for (Integer childId : node.childs) {
                        sum += chain.nodes[childId].maxValue;
                        min = Math.min(min, chain.nodes[childId].maxValue);
                    }
                    node.maxValue = Math.max(min, node.value);
                    fun += sum - min;
                }
                node.processed = true;
                if (chain.holes.contains(nodeId)) {
                    fun += node.maxValue;
                }
            }
        }
        return fun;
    }

    public static class ChainNode {
        int value;
        int maxValue;
        boolean processed = false;
        boolean visited = false;
        List<Integer> childs;

        public ChainNode(int value) {
            this.value = value;
            this.childs = new ArrayList<>();
        }
    }

    public static class Chain {
        ChainNode[] nodes;
        Set<Integer> holes;

        public Chain(int nodesN) {
            nodes = new ChainNode[nodesN];
            holes = new HashSet<>();
        }
    }

    private static Chain readChain(Scanner aIn) {
        int t = Integer.parseInt(aIn.nextLine());
        Chain chain = new Chain(t);
        String[] myValues = aIn.nextLine().split(" ");
        String[] myParents = aIn.nextLine().split(" ");
        for (int i = 0; i < myValues.length; i++) {
            int myValue = Integer.parseInt(myValues[i]);
            int myParent = Integer.parseInt(myParents[i]);
            chain.nodes[i] = new ChainNode(myValue);
            if (myParent == 0) {
                chain.holes.add(i);
            } else {
                chain.nodes[myParent - 1].childs.add(i);
            }
        }
        return chain;
    }
}

package Graphs;

import java.util.*;

class Pair {
    int node, weight;
    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class PrimsAlgorithm{
    public static int primsMST(int V, List<List<Pair>> adj) {
        boolean[] visited = new boolean[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        pq.add(new Pair(0, 0));
        int totalWeight = 0;

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int node = current.node;
            int wt = current.weight;

            if (visited[node]) continue;
            visited[node] = true;
            totalWeight += wt;

            for (Pair neighbor : adj.get(node)) {
                if (!visited[neighbor.node]) {
                    pq.add(new Pair(neighbor.node, neighbor.weight));
                }
            }
        }
        return totalWeight;
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {
            {0, 1, 2}, {0, 2, 1}, {1, 2, 1},
            {2, 3, 2}, {3, 4, 1}, {4, 2, 2}
        };

        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }

        int result = primsMST(V, adj);
        System.out.println("Minimum Spanning Tree Weight: " + result);
    }
}


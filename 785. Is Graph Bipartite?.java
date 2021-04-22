/*
There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.



Example 1:


Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
Example 2:


Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.


Constraints:

graph.length == n
1 <= n <= 100
0 <= graph[u].length < n
0 <= graph[u][i] <= n - 1
graph[u] does not contain u.
All the values of graph[u] are unique.
If graph[u] contains v, then graph[v] contains u.
 */


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution785 {
    public boolean isBipartite(int[][] graph) {
        Map<Integer, Integer> map = new HashMap<>();
        if (graph == null || graph.length == 0) {
            return true;
        }
        for (int i = 0; i < graph.length; i++) {
            if (!bfs(graph, map, i)) {
                return false;
            }
        }
        return true;
    }

    boolean bfs(int[][] graph, Map<Integer, Integer> map, int curr) {
        if (map.containsKey(curr)) {
            return true;
        }

        map.put(curr, 0);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(curr);


        while (!queue.isEmpty()) {
            int temp = queue.poll();
            int color = map.get(temp);
            int otherColor = (color + 1) % 2;
            for (int neighbor: graph[temp]) {
                if (map.containsKey(neighbor)) {
                    if (map.get(neighbor) != otherColor) {
                        return false;
                    }
                } else {
                    queue.offer(neighbor);
                    map.put(neighbor, otherColor);
                }
            }


        }
        return true;



    }


}
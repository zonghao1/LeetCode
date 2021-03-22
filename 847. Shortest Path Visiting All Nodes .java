//An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
//
//        graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
//
//        Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
//
//
//
//        Example 1:
//
//        Input: [[1,2,3],[0],[0],[0]]
//        Output: 4
//        Explanation: One possible path is [1,0,2,0,3]
//        Example 2:
//
//        Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
//        Output: 4
//        Explanation: One possible path is [0,1,4,2,3]

import java.util.LinkedList;
import java.util.Queue;

class Solution847 {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] matrix = new boolean[n][1<<n];
        for (int i = 0; i < n; i++) {
            int[] curr = new int[]{i, 1<<i};
            queue.offer(curr);
        }
        int step = 0;
        int target = (1<<n) - 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            String a = "";
            while (size > 0) {
                size--;
                int[] curr = queue.poll();
                if (curr[1] == target) {
                    return step;
                }
                if (matrix[curr[0]][curr[1]]) {
                    continue;
                }
                matrix[curr[0]][curr[1]] = true;
                int[] nextPart = graph[curr[0]];
                for (int i: nextPart) {
                    queue.offer(new int[]{i, (curr[1] | (1 << i))});
                }

            }
            step++;
        }
        return -1;

    }
}
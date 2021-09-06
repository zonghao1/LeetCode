/*
You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.



Example 1:


Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1


Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */

import java.util.*;

class Solution743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] time: times) {
            map.putIfAbsent(time[0], new ArrayList<>());
            map.get(time[0]).add(time);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new networkComparator());
        pq.offer(new int[]{0, k, 0});
        boolean[] visited = new boolean[n+1];


        int max = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (visited[curr[1]]) continue;
            visited[curr[1]] = true;


            List<int[]> list = map.getOrDefault(curr[1], new ArrayList<>());


            for (int[] neighbor: list) {
                if (!visited[neighbor[1]]) {
                    pq.offer(new int[]{neighbor[0], neighbor[1], curr[2] + neighbor[2]});
                }
            }
            max = curr[2];

        }

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) return -1;

        }
        return max;


    }









    class networkComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            if (a[2] == b[2]) return 0;
            return a[2] - b[2];
        }


    }


}
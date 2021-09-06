/*

There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.



Example 1:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation: The graph is shown.
The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation: The graph is shown.
The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.


Constraints:

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between two cities.
0 <= src, dst, k < n
src != dst
 */


import java.util.*;

class Solution787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] flight: flights) {
            map.putIfAbsent(flight[0], new ArrayList<>());
            map.get(flight[0]).add(flight);
        }

        int[][] price = new int[n+1][n+1];

        pq.offer(new int[]{n, src, 0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int source = curr[0];
            int destination = curr[1];
            int currPrice = curr[2];
            int stops = curr[3];
            if (stops > k + 1 || price[destination][stops] != 0) continue;
            if (destination == dst) {
                return currPrice;
            }
            price[destination][stops] = currPrice;

            List<int[]> list = map.getOrDefault(destination, new ArrayList<>());
            for (int[] next: list) {
                pq.offer(new int[]{destination, next[1], currPrice + next[2], stops+1});
            }
        }

        return -1;
    }
}

 class Solution787a {
     public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
         int[][] visited = new int[n][k + 1];
         for (int[] each : visited) Arrays.fill(each, Integer.MAX_VALUE);
         Map<Integer, List<int[]>> graph = new HashMap<>();
         for (int[] route : flights) {
             int from = route[0];
             int to = route[1];
             int cost = route[2];
             graph.putIfAbsent(from, new ArrayList<>());
             graph.putIfAbsent(to, new ArrayList<>());
             graph.get(from).add(new int[]{to, cost});
         }


         Queue<int[]> qItems = new PriorityQueue<>((a, b) -> a[1] - b[1]);
         qItems.add(new int[] {src, 0, 0});
         while (!qItems.isEmpty()) {
             int[] curr = qItems.poll();
             int currCity = curr[0];
             int cost = curr[1];
             int stopsTaken = curr[2];
             if (currCity == dst) return cost;
             if (stopsTaken > k) continue;
             for (int[] adjPair : graph.get(currCity)) {
                 int adjCity = adjPair[0];
                 int newCost = adjPair[1] + cost;
                 if (visited[adjCity][stopsTaken] > newCost) {
                     qItems.add(new int[]{adjCity, newCost, stopsTaken + 1});
                     visited[adjCity][stopsTaken] = newCost;
                 }
             }
         }

         return -1;
     }
 }
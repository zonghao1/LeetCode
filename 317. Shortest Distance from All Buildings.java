/*
You are given an m x n grid grid of values 0, 1, or 2, where:

each 0 marks an empty land that you can pass by freely,
each 1 marks a building that you cannot pass through, and
each 2 marks an obstacle that you cannot pass through.
You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.

Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.

The total travel distance is the sum of the distances between the houses of the friends and the meeting point.

The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.



Example 1:


Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 7
Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
So return 7.
Example 2:

Input: grid = [[1,0]]
Output: 1
Example 3:

Input: grid = [[1]]
Output: -1


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
grid[i][j] is either 0, 1, or 2.
There will be at least one building in the grid.
 */

import java.util.LinkedList;
import java.util.Queue;

class Solution317 {
    public int shortestDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] nums = new int[rows][cols];
        int[][] dist = new int[rows][cols];
        int houses = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    houses++;
                    boolean[][] visited = new boolean[rows][cols];
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{row, col});
                    visited[row][col] = true;
                    int step = 0;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            int[] curr = queue.poll();
                            int i = curr[0];
                            int j = curr[1];
                            if (grid[i][j] == 0) {
                                nums[i][j]++;
                                dist[i][j] += step;
                            }
                            if ( i + 1 < rows && grid[i+1][j] == 0 && !visited[i+1][j]) {
                                queue.offer(new int[]{i+1,j});
                                visited[i+1][j] = true;
                            }
                            if ( i - 1 >= 0 && grid[i-1][j] == 0 && !visited[i-1][j]) {
                                queue.offer(new int[]{i-1,j});
                                visited[i-1][j] = true;
                            }
                            if (j + 1 < cols && grid[i][j+1] == 0 && !visited[i][j+1]) {
                                queue.offer(new int[]{i, j+1});
                                visited[i][j+1] = true;
                            }
                            if (j - 1 >= 0 && grid[i][j-1] == 0 && !visited[i][j-1]) {
                                queue.offer(new int[]{i, j-1});
                                visited[i][j-1] = true;
                            }
                        }
                        step++;
                    }

                }

            }
        }

        int ret = Integer.MAX_VALUE;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (nums[row][col] == houses) {
                    ret = Math.min(ret, dist[row][col]);
                }
            }
        }
        return ret == Integer.MAX_VALUE? -1 : ret;


    }
}
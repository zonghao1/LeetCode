/*
You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.



Example 1:

Input:
grid =
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]],
k = 1
Output: 6
Explanation:
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
Example 2:

Input:
grid =
[[0,1,1],
 [1,1,1],
 [1,0,0]],
k = 1
Output: -1
Explanation:
We need to eliminate at least two obstacles to find such a walk.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 40
1 <= k <= m * n
grid[i][j] == 0 or 1
grid[0][0] == grid[m - 1][n - 1] == 0
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution1293 {
    int[] DIRS = new int[]{0, -1, 0, 1, 0};

    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] min = new int[rows][cols];
        for (int[] row: min) Arrays.fill(row, rows * cols);
        Queue<int[]> queue = new LinkedList<>();
        min[0][0] = 0;
        queue.offer(new int[]{0,0,0});
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] curr = queue.poll();
                int row = curr[0];
                int col = curr[1];
                int obs = curr[2];

                if (row == rows - 1 && col == cols - 1) return step;

                for (int i = 0; i < 4; i++) {
                    int newRow = row + DIRS[i];
                    int newCol = col + DIRS[i+1];
                    int newObs = obs + grid[row][col];
                    if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || newObs > k || newObs >= min[newRow][newCol]) {
                        continue;
                    } else {
                        min[newRow][newCol] = newObs;
                        queue.offer(new int[]{newRow, newCol, newObs});
                    }
                }


                size--;
            }
            step++;

        }
        return -1;

    }
}
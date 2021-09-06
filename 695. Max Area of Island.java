/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.



Example 1:


Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
 */

class Solution695 {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    grid[row][col] = -1;
                    max = Math.max(max, dfs(grid, rows, cols, row, col));
                }

            }

        }
        return max;
    }

    int dfs(int[][] grid, int rows, int cols, int row, int col) {
        int ret = 1;
        if (valid(grid, rows, cols, row + 1, col)) {
            grid[row+1][col] = -1;
            ret += dfs(grid, rows, cols, row+1, col);
        }
        if (valid(grid, rows, cols, row - 1, col)) {
            grid[row-1][col] = -1;
            ret += dfs(grid, rows, cols, row-1, col);
        }
        if (valid(grid, rows, cols, row, col + 1)) {
            grid[row][col+1] = -1;
            ret += dfs(grid, rows, cols, row, col+1);
        }
        if (valid(grid, rows, cols, row, col-1)) {
            grid[row][col-1] = -1;
            ret += dfs(grid, rows, cols, row, col-1);
        }
        return ret;
    }



    boolean valid(int[][] grid, int rows, int cols, int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 1 ;
    }


}
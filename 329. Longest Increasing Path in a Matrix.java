/*
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).



Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1
 */

import java.util.Arrays;

class Solution329 {
    int[] DIRS = new int[]{0, -1, 0, 1, 0};

    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] ret = new int[rows][cols];
        for (int[] row: ret) Arrays.fill(row, -1);

        int ans = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                ans = Math.max(ans, dfs(matrix, ret, row, col));
            }
        }
        return ans;

    }

    int dfs(int[][] matrix, int[][] ret, int row, int col) {
        if (ret[row][col] != -1) return ret[row][col];
        ret[row][col] = 1;
        for (int i = 0; i < 4; i++) {
            int newRow = row + DIRS[i];
            int newCol = col + DIRS[i+1];
            if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length && matrix[newRow][newCol] > matrix[row][col]) {
                ret[row][col] = Math.max(ret[row][col], dfs(matrix, ret, newRow, newCol) + 1);
            }
        }
        return ret[row][col];



    }


}
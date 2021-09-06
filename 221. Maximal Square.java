/*
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.



Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
Example 2:


Input: matrix = [["0","1"],["1","0"]]
Output: 1
Example 3:

Input: matrix = [["0"]]
Output: 0


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.
 */

class Solution221 {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (matrix == null || rows == 0 || cols == 0) {
            return 0;
        }
        int[] prev = new int[cols];
        int[] curr = new int[cols];
        int max = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == '0' || row == 0 || col == 0) {
                    curr[col] = matrix[row][col] - '0';
                } else {
                    curr[col] = Math.min(prev[col-1], Math.min(curr[col-1], prev[col])) + 1;
                }
                max = Math.max(max, curr[col]);
            }
            prev = curr;
            curr = new int[cols];
        }
        return max * max;
    }
}
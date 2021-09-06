/*
Given an m x n binary matrix mat, return the length of the longest line of consecutive one in the matrix.

The line could be horizontal, vertical, diagonal, or anti-diagonal.



Example 1:


Input: mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
Output: 3
Example 2:


Input: mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
Output: 4


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
 */

class Solution562 {
    public int longestLine(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        int max = 0;
        for (int row = 0; row < rows; row++) {
            int curr = 0;
            for (int col = 0; col < cols; col++) {
                if (mat[row][col] == 1) {
                    curr += 1;
                    max = Math.max(max, curr);
                } else {
                    curr = 0;
                }
            }
        }

        for (int col = 0; col < cols; col++) {
            int curr = 0;
            for (int row = 0; row < rows; row++) {
                if (mat[row][col] == 1) {
                    curr += 1;
                    max = Math.max(max, curr);
                } else curr = 0;
            }
        }

        int[] prev = new int[cols];
        for (int row = 0; row < rows; row++) {
            int[] curr = new int[cols];
            for (int col = 0; col < cols; col++) {
                if (col - 1 >= 0 && mat[row][col] == 1) {
                    curr[col] = prev[col-1] + 1;
                    max = Math.max(max, curr[col]);
                }
            }
            prev = curr;
        }
        prev = new int[cols];
        for (int row = 0; row < rows; row++) {
            int[] curr = new int[cols];
            for (int col = cols - 1; col >= 0; col--) {
                if (col + 1 < cols && mat[row][col] == 1) {
                    curr[col] = prev[col+1] + 1;
                    max = Math.max(max, curr[col]);
                }
            }
            prev = curr;
        }
        return max;



    }
}
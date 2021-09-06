/*

Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.



Example 1:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9
Example 2:

Input: grid = [[1,1,0,0]]
Output: 1


Constraints:

1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] is 0 or 1
 */

class Solution1139 {
    public int largest1BorderedSquare(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] right = new int[rows][cols];
        int[][] up    = new int[rows][cols];
        int max = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = cols - 1; col >= 0; col--) {
                if (col == cols - 1 || grid[row][col] == 0) {
                    right[row][col] = grid[row][col];
                } else {
                    right[row][col] = right[row][col+1] + 1;
                }
            }
        }
        for (int col = 0; col < cols; col++) {
            for (int row = rows - 1; row >= 0; row--) {
                if (row == rows - 1 || grid[row][col] == 0) {
                    up[row][col] = grid[row][col];
                } else {
                    up[row][col] = up[row+1][col] + 1;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 0) {
                    continue;
                }
                int maxLength = Math.min(right[row][col], up[row][col]);
                for (int length = maxLength; length >= 1; length--) {
                    if (up[row][col+length-1] >= length && right[row+length-1][col] >= length) {
                        max = Math.max(max, length);
                        break;
                    }
                }


            }
        }
        return max * max;


    }
}
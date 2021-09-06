/*
On a 2 dimensional grid with rows rows and cols columns, we start at (rStart, cStart) facing east.

Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.

Now, we walk in a clockwise spiral shape to visit every position in this grid.

Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)

Eventually, we reach all rows * cols spaces of the grid.

Return a list of coordinates representing the positions of the grid in the order they were visited.



Example 1:

Input: rows = 1, cols = 4, rStart = 0, cStart = 0
Output: [[0,0],[0,1],[0,2],[0,3]]




Example 2:

Input: rows = 5, cols = 6, rStart = 1, cStart = 4
Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]




Note:

1 <= rows <= 100
1 <= cols <= 100
0 <= rStart < rows
0 <= cStart < cols
 */

class Solution885 {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int n = rows * cols;
        int[][] ret = new int[n][2];
        int index = 1;
        helper(ret, 0, rows, cols, rStart, cStart, 1);
        return ret;

    }


    void helper(int[][] ret, int index, int rows, int cols, int row, int col, int length) {
        if (index >= rows * cols) return;

        for (int i = 0; i < length; i++) {
            if (valid(row, col++, rows, cols)) {
                ret[index++] = new int[]{row, col-1};
            }
        }
        for (int i = 0; i < length; i++) {
            if (valid(row++,col, rows, cols)) {
                ret[index++] = new int[]{row-1, col};
            }
        }
        length++;
        for (int i = 0; i < length; i++) {
            if (valid(row, col--, rows, cols)) {
                ret[index++] = new int[]{row, col+1};
            }
        }

        for (int i = 0; i < length; i++) {
            if (valid(row--, col, rows, cols)) {
                ret[index++] = new int[]{row+1, col};
            }
        }
        helper(ret, index, rows, cols, row, col, length + 1);


    }

    boolean valid(int row, int col, int rows, int cols) {
        if (row < 0 || col < 0 || row >= rows|| col >= cols) {
            return false;
        }
        return true;
    }



}
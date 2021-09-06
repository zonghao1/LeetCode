/*
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.



Example 1:


Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
Example 2:

Input: n = 1
Output: 1


Constraints:

1 <= n <= 9
 */

import java.util.ArrayList;
import java.util.List;

class Solution52a {
    public int totalNQueens(int n) {
        int[] ret = new int[1];
        boolean[] cols = new boolean[n];
        boolean[] upRight = new boolean[2 * n - 1];
        boolean[] upLeft = new boolean[2 * n - 1];
        dfs(ret, new ArrayList<>(), cols, upRight, upLeft, 0, n);
        return ret[0];
    }

    void dfs(int[] ret, List<Integer> curr, boolean[] cols, boolean[] upRight, boolean[] upLeft, int row, int n) {
        if (row == n) {
            ret[0] += 1;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols[col] || upRight[row + col] || upLeft[col-row+n-1]) {
                continue;
            }
            changeStatus(row, col, cols, upRight, upLeft,n);
            dfs(ret, curr, cols, upRight, upLeft, row + 1, n);
            changeStatus(row, col, cols, upRight, upLeft,n);
        }
    }

    boolean changeStatus(int row, int col, boolean[] cols, boolean[] upRight, boolean[] upLeft, int n) {

        cols[col] =  !cols[col];
        upRight[row + col] = !upRight[row + col];
        upLeft[col-row+n-1] = !upLeft[col-row+n-1];
        return true;
    }

}

class Solution52b {
    public int totalNQueens(int n) {
        int[] ret = new int[1];
        boolean[] cols = new boolean[n];
        boolean[] upRight = new boolean[2 * n - 1];
        boolean[] upLeft = new boolean[2 * n - 1];
        dfs(ret, new ArrayList<>(), cols, upRight, upLeft, 0, n);
        return ret[0];
    }

    void dfs(int[] ret, List<Integer> curr, boolean[] cols, boolean[] upRight, boolean[] upLeft, int row, int n) {
        if (row == n) {
            ret[0] += 1;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (row == 0) {
                if (n % 2 == 0) {
                    if (col == n / 2 ) {
                        ret[0] *=2;
                        return;
                    }
                } else {
                    if (col == n / 2) {
                        ret[0] *= 2;
                    } else if (col == n / 2 + 1) {
                        return;
                    }
                }
            }

            if (cols[col] || upRight[row + col] || upLeft[col-row+n-1]) {
                continue;
            }
            changeStatus(row, col, cols, upRight, upLeft,n);
            dfs(ret, curr, cols, upRight, upLeft, row + 1, n);
            changeStatus(row, col, cols, upRight, upLeft,n);
        }
    }







    boolean changeStatus(int row, int col, boolean[] cols, boolean[] upRight, boolean[] upLeft, int n) {

        cols[col] =  !cols[col];
        upRight[row + col] = !upRight[row + col];
        upLeft[col-row+n-1] = !upLeft[col-row+n-1];
        return true;
    }

}


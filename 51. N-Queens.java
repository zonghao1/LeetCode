import java.util.ArrayList;
import java.util.List;

/*
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.



Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]


Constraints:

1 <= n <= 9
 */
class Solution51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret = new ArrayList<>();
        boolean[] cols = new boolean[n];
        boolean[] upRight = new boolean[2 * n - 1];
        boolean[] upLeft = new boolean[2 * n - 1];
        dfs(ret, new ArrayList<>(), cols, upRight, upLeft, 0, n);
        return ret;
    }

    void dfs(List<List<String>> ret, List<Integer> curr, boolean[] cols, boolean[] upRight, boolean[] upLeft, int row, int n) {
        if (row == n) {
            List<String> currRet = new ArrayList<>();
            for (int currRow = 0; currRow < n; currRow++) {
                StringBuilder sb = new StringBuilder();
                int place = curr.get(currRow);
                for (int col = 0; col < n; col++) {
                    if (col == place) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                currRet.add(sb.toString());
            }
            ret.add(currRet);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols[col] || upRight[row + col] || upLeft[col-row+n-1]) {
                continue;
            }
            changeStatus(row, col, cols, upRight, upLeft,n);
            curr.add(col);
            dfs(ret, curr, cols, upRight, upLeft, row + 1, n);
            curr.remove(curr.size() - 1);
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
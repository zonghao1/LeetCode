/*
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.



Example 1:


Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]


Constraints:

1 <= n <= 20
 */

class Solution59 {
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int left = 0;
        int right = n - 1;
        int up = 0;
        int bottom = n - 1;
        helper(ret, left, right, up, bottom, 1);
        return ret;
    }

    void helper(int[][] ret, int left, int right, int up, int bottom, int number){
        if (left > right) return;
        if (left == right) {
            ret[up][left] = number;
            return;
        }

        for (int i = left; i < right; i++) {
            ret[up][i] = number++;
        }

        for (int i = up; i < bottom; i++) {
            ret[i][right] = number++;
        }

        for (int i = right; i > left; i--) {
            ret[bottom][i] = number++;
        }

        for (int i = bottom; i > up; i--) {
            ret[i][left] = number++;
        }

        helper(ret, left + 1, right - 1, up + 1, bottom - 1, number);
    }




}
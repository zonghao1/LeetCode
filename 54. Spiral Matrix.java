/*
Given an m x n matrix, return all elements of the matrix in spiral order.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 */

import java.util.ArrayList;
import java.util.List;

class Solution54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length - 1;
        int up = 0;
        int bottom = matrix.length - 1;
        List<Integer> ret = new ArrayList<>();
        helper(ret, matrix, left, right, up, bottom);
        return ret;

    }

    void helper(List<Integer> ret, int[][] matrix, int left, int right, int up, int bottom) {
        if (left > right || up > bottom) return;

        if (left == right) {
            for (int i = up; i <= bottom; i++) {
                ret.add(matrix[i][left]);
            }
            return;
        }

        if (up == bottom) {
            for (int i = left; i <= right; i++) {
                ret.add(matrix[up][i]);
            }
            return;
        }


        for (int i = left; i < right; i++) {
            ret.add(matrix[up][i]);
        }

        for (int i = up; i < bottom; i++) {
            ret.add(matrix[i][right]);
        }

        for (int i = right; i > left; i--) {
            ret.add(matrix[bottom][i]);
        }

        for (int i = bottom ; i > up; i--) {
            ret.add(matrix[i][left]);
        }

        helper(ret, matrix, left + 1, right - 1, up + 1, bottom - 1);


    }



}
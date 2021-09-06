/*
Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.

It is guaranteed that there will be a rectangle with a sum no larger than k.



Example 1:


Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2
Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
Example 2:

Input: matrix = [[2,2,-1]], k = 3
Output: 3


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-100 <= matrix[i][j] <= 100
-105 <= k <= 105


Follow up: What if the number of rows is much larger than the number of columns?


 */

import java.util.TreeSet;

class Solution363 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] colSum = new int[rows][cols];
        int ret = Integer.MIN_VALUE;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0) {
                    colSum[row][col] = matrix[row][col];
                } else {
                    colSum[row][col] = matrix[row][col] + colSum[row-1][col];
                }
            }
        }

        for (int topRow = 0; topRow < rows; topRow++) {
            for (int bottomRow = topRow; bottomRow < rows; bottomRow++) {
                int prev = 0;
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for (int col = 0; col < cols; col++) {
                    int curr = topRow == 0? colSum[bottomRow][col] : colSum[bottomRow][col] - colSum[topRow-1][col];
                    curr += prev;
                    Integer larger = set.ceiling(curr - k);
                    if (larger != null) {
                        ret = Math.max(ret, curr - larger);
                    }
                    prev = curr;
                    set.add(curr);

                }


            }


        }
        return ret;





    }
}
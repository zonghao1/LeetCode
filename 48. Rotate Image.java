/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
Example 3:

Input: matrix = [[1]]
Output: [[1]]
Example 4:

Input: matrix = [[1,2],[3,4]]
Output: [[3,1],[4,2]]


Constraints:

matrix.length == n
matrix[i].length == n
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
 */

class Solution48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        helper(matrix, 0, n - 1, 0, n - 1);
        return;
    }


    void helper(int[][] matrix, int left, int right, int up, int bottom) {
        if (left >= right) return;

        int length = right - left;
        int[] temp = new int[length];

        for (int i = 0; i < length; i++) {
            temp[i] = matrix[up][left + i];
        }

        for (int i = 0; i < length; i++) {
            int tempNumber = temp[i];
            temp[i] = matrix[up+i][right];
            matrix[up+i][right] = tempNumber;
        }

        for (int i = 0; i < length; i++) {
            int tempNumber = temp[i];
            temp[i] = matrix[bottom][right-i];
            matrix[bottom][right-i] = tempNumber;
        }

        for (int i = 0; i < length; i++) {
            int tempNumber = temp[i];
            temp[i] = matrix[bottom-i][left];
            matrix[bottom-i][left] = tempNumber;
        }

        for (int i = 0; i < length; i++) {
            matrix[up][left + i] = temp[i];
        }

        helper(matrix, left + 1, right - 1, up + 1, bottom - 1);


    }

}
/*
Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.


Example 1:


Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true
Example 2:


Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
Output: false


Constraints:

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matix[i][j] <= 109
All the integers in each row are sorted in ascending order.
All the integers in each column are sorted in ascending order.
-109 <= target <= 109

 */

class Solution240 {
    // 走一遍，类似BST，O(m+n)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = cols - 1;
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }
}

class Solution240a {
    //简单binary search
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int row = 0; row < rows; row++) {
            if (binarySearch(matrix, row, target)) {
                return true;
            }
        }
        return false;
    }

    boolean binarySearch(int[][] matrix, int row, int target) {
        int l = 0;
        int r = matrix[0].length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}


class Solution240b {

    //分四个部分，每次去掉一个
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        return search(matrix, 0, 0, matrix[0].length - 1, matrix.length - 1, target);
    }

    boolean search(int[][] matrix, int left, int top, int right, int bot, int target) {
        if (left > right || top > bot) {
            return false;
        }

        int row = top + (bot - top) / 2;
        int col = left + (right - left) / 2;
        if (matrix[row][col] == target) {
            return true;
        }
        if (matrix[row][col] < target) {
            return search(matrix, col + 1, top, right, row, target) || search(matrix, left, row + 1, col, bot, target) || search(matrix, left + 1, row + 1, right, bot, target);
        } else {
            return search(matrix, left, top, col - 1, row - 1, target) || search(matrix, left, row, col - 1, bot, target) || search(matrix, col, top, right, row-1, target);
        }
    }

}
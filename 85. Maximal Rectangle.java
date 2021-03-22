//Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
//
//
//
//        Example 1:
//
//
//        Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
//        Output: 6
//        Explanation: The maximal rectangle is shown in the above picture.
//        Example 2:
//
//        Input: matrix = []
//        Output: 0
//        Example 3:
//
//        Input: matrix = [["0"]]
//        Output: 0

import java.util.Stack;

class Solution85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] height = new int[cols + 2];
        int max = 0;
        for (int row = 0; row < rows; row++) {
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            for (int col = 0; col <= cols; col++) {
                if (col == cols || matrix[row][col] == '0') {
                    height[col + 1] = 0;
                } else {
                    height[col + 1] = height[col + 1] + 1;
                }
                if (stack.isEmpty() || height[col + 1] >= height[col]) {
                    stack.push(col + 1);
                } else {
                    while (!stack.isEmpty() && height[col + 1] < height[stack.peek()]) {
                        int right = col + 1;
                        int currHeight = height[stack.pop()];
                        int left = stack.peek();
                        int square = currHeight * (right - left - 1);
                        max = Math.max(max, square);
                    }
                    stack.push(col + 1);
                }


            }
        }
        return max;
    }
}
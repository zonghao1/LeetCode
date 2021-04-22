//Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
//
//
//
//        Example 1:
//
//
//        Input: heights = [2,1,5,6,2,3]
//        Output: 10
//        Explanation: The above is a histogram where width of each bar is 1.
//        The largest rectangle is shown in the red area, which has an area = 10 units.

import java.util.Stack;

class Solution84 {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        int[] newHeights = new int[heights.length + 2];
        for (int i = 0; i < heights.length; i++) {
            newHeights[i+1] = heights[i];
        }

        for (int i = 0; i < newHeights.length; i++) {
            if (stack.isEmpty() || newHeights[i] >= newHeights[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                    int right = i;
                    int height = newHeights[stack.pop()];
                    int left = stack.peek();
                    int square = height * (right - left - 1);
                    max = Math.max(max, square);
                }
                stack.push(i);
            }
        }
        return max;
    }
}
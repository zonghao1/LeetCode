/*
There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.

For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
Return the minimum cost to paint all houses.



Example 1:

Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
Minimum cost: 2 + 5 + 3 = 10.
Example 2:

Input: costs = [[7,6,2]]
Output: 2


Constraints:

costs.length == n
costs[i].length == 3
1 <= n <= 100
1 <= costs[i][j] <= 20
 */

class Solution256 {
    public int minCost(int[][] costs) {
        int rows = costs.length;
        int cols = 3;
        int[] prev = new int[3];

        for (int row = 0; row < rows; row++) {
            int[] temp = new int[3];
            for (int col = 0; col < cols; col++) {
                int min = Integer.MAX_VALUE;
                for (int preCol = 0; preCol < cols; preCol++) {
                    if (preCol != col) {
                        min = Math.min(min, prev[preCol]);
                    }
                }
                temp[col] = costs[row][col] + min;
            }
            prev = temp;
        }
        int ret = prev[0];
        for (int col = 0; col < cols; col++) {
            ret = Math.min(ret, prev[col]);
        }
        return ret;
    }
}
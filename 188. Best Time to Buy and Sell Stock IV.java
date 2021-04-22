/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.


Constraints:

0 <= k <= 100
0 <= prices.length <= 1000
0 <= prices[i] <= 1000
 */

class Solution188a {
    // kn^2 超时算法

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int days = prices.length;
        int[][] matrix = new int[k+1][days];
        for (int row = 1; row < matrix.length; row++) {
            for (int day = 1; day < days; day++) {

                for (int prev = day - 1; prev >= 0; prev--) {

                    matrix[row][day] = Math.max(matrix[row][day], matrix[row-1][prev] + profit(prices, prev, day));
                }
            }
        }

        return matrix[matrix.length - 1][matrix[0].length - 1];
    }

    int profit(int[] prices, int start, int end) {
        int ret = 0;
        int low = prices[start];
        for (int i = start; i <= end; i++) {
            ret = Math.max(ret, prices[i] - low);
            low = Math.min(low, prices[i]);
        }
        return ret;
    }

}

class Solution188b {
    //
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int days = prices.length;
        int[][] matrix = new int[k+1][days];
        for (int row = 1; row <= k; row++) {
            int maxDiff = -prices[0];
            for (int day = 1; day < prices.length; day++) {
                matrix[row][day] = Math.max(prices[day] + maxDiff, matrix[row][day-1]);
                maxDiff = Math.max(maxDiff, matrix[row-1][day] - prices[day]);
            }
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }
}
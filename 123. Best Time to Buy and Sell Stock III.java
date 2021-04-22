/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
Example 4:

Input: prices = [1]
Output: 0


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
 */


class Solution123a {
    // 套用 k的算法
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int days = prices.length;
        int[] prev = new int[days];
        int[] curr = new int[days];

        for (int row = 1; row <= 2; row++) {
            int maxDiff = -prices[0];
            for (int day = 1; day < prices.length; day++) {
                curr[day] = Math.max(prices[day] + maxDiff, curr[day-1]);
                maxDiff = Math.max(maxDiff, prev[day] - prices[day]);
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[days-1];
    }
}

class Solution123b {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int days = prices.length;
        int[] left = new int[days];
        int[] right = new int[days];
        int low = prices[0];
        int high = prices[days-1];

        for (int i = 1; i < days; i++) {
            left[i] = Math.max(left[i-1], prices[i] - low);
            low = Math.min(low, prices[i]);
        }

        for (int j = days - 2; j >= 0; j--) {
            right[j] = Math.max(right[j+1], high - prices[j]);
            high = Math.max(high, prices[j]);
        }
        int ret = 0;
        for (int i = 0; i < days; i++) {
            ret = Math.max(ret, left[i] + right[i]);
        }
        return ret;
    }
}
/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0


Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000

 */

class Solution309 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int days = prices.length;
        int[] hold = new int[days+1];
        int[] unhold = new int[days+1];

        hold[1] = -prices[0];
        for (int day = 2; day <= days; day++) {
            hold[day] = Math.max(hold[day-1], unhold[day-2] - prices[day-1]);
            unhold[day] = Math.max(unhold[day-1], hold[day-1] + prices[day-1]);
        }
        return unhold[days];
    }
}
/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.



Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0
Example 4:

Input: coins = [1], amount = 1
Output: 1
Example 5:

Input: coins = [1], amount = 2
Output: 2
 */

import java.util.Arrays;

class Solution322 {
    public int coinChange(int[] coins, int amount) {
        int[] ret = new int[amount + 1];
        for (int i = 1; i < ret.length; i++) {
            ret[i] = amount + 1;
        }
        for (int coin: coins) {
            for (int k = 1; k  <= amount; k++) {
                if (k - coin >= 0) {
                    ret[k] = Math.min(ret[k], ret[k - coin] + 1);
                }
            }
        }
        if (ret[ret.length - 1] == amount + 1) {
            return -1;
        }
        return ret[ret.length - 1];
    }
}


/*
    DFS + 强剪枝
 */
class Solution332a {
    public int coinChange(int[] coins, int amount) {
        int[] ret = new int[]{Integer.MAX_VALUE};
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0, ret);
        if (ret[0] == Integer.MAX_VALUE) {
            return -1;
        }
        return ret[0];
    }

    void dfs(int[] coins, int level, int left, int used, int[] ret) {
        if (level == 0) {
            if (left % coins[0] == 0) {
                ret[0] = Math.min(ret[0], used + left / coins[0]);
            }
            return;
        }
        int max = left / coins[level];
        for (int k = max; k >= 0 && k + used < ret[0]; k--) {
            dfs(coins, level - 1, left - coins[level] * k, used + k, ret);

        }

    }
}
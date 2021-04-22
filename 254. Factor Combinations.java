/*
Numbers can be regarded as the product of their factors.

For example, 8 = 2 x 2 x 2 = 2 x 4.
Given an integer n, return all possible combinations of its factors. You may return the answer in any order.

Note that the factors should be in the range [2, n - 1].



Example 1:

Input: n = 1
Output: []
Example 2:

Input: n = 12
Output: [[2,6],[3,4],[2,2,3]]
Example 3:

Input: n = 37
Output: []
Example 4:

Input: n = 32
Output: [[2,16],[4,8],[2,2,8],[2,4,4],[2,2,2,4],[2,2,2,2,2]]
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution254 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> factors = new ArrayList<>();
        if (n <= 1) {
            return ret;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            int remain = n / i;
            if (i * remain == n) {
                factors.add(i);
                if (i != remain) {
                    factors.add(remain);
                }
            }
        }
        Collections.sort(factors);
        Collections.reverse(factors);
        dfs(ret, new ArrayList<>(), factors, n, 1, 0);
        return ret;

    }

    void dfs(List<List<Integer>> ret, List<Integer> curr, List<Integer> factors, int n, int currValue, int level) {
        if (level == factors.size()) {
            if (n == 1) {
                ret.add(new ArrayList<>(curr));
            }
            return;
        }

        int i = 0;
        for (; n >= 1; i++) {

            dfs(ret, curr, factors, n, currValue, level + 1);
            if (n % factors.get(level) != 0) {
                break;
            }
            curr.add(factors.get(level));
            n /= factors.get(level);

        }

        while (i > 0) {
            curr.remove(curr.size() - 1);
            i--;
        }


    }



}
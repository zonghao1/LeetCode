//Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
//
//
//
//        Example 1:
//
//
//        Input: n = 3
//        Output: 5
//        Example 2:
//
//        Input: n = 1
//        Output: 1

class Solution96 {
    public int numTrees(int n) {
        int[] ret = new int[n + 1];
        ret[0] = 1;
        ret[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int left = 0; left < i; left++) {
                ret[i] += ret[left] * ret[i - left - 1];
            }
        }
        return ret[ret.length - 1];
    }
}
/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.



Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1


Constraints:

1 <= s.length <= 2000
s consists of lower-case English letters only.
 */

class Solution132 {
    public int minCut(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            valid(s, dp, i, i);
            valid(s, dp, i, i + 1);
        }

        int[] ret = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            ret[i] = i;
            for (int j = 0; j <= i; j++) {
                if (dp[j][i]) {
                    ret[i] = j == 0? 0 : Math.min(ret[i], ret[j-1] + 1);
                }
            }
        }
        return ret[ret.length - 1];
    }

    void valid(String s, boolean[][] dp, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            dp[i][j] = true;
            i--;
            j++;
        }
        return;
    }


}
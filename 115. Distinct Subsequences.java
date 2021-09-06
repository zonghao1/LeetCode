/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.

A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).

It is guaranteed the answer fits on a 32-bit signed integer.



Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
babgbag
babgbag
babgbag
babgbag
babgbag


Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
 */

class Solution115 {
    public int numDistinct(String s, String t) {
        int rows = t.length();
        int cols = s.length();
        int[][] ret = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {


                if (row == 0) {
                    if (s.charAt(col) == t.charAt(row)) {
                        ret[row][col] = 1;
                    }
                } else {
                    int curr = 0;
                    if (s.charAt(col) != t.charAt(row)) {
                        continue;
                    }
                    for (int i = col - 1; i >= 0; i--) {
                        curr += ret[row-1][i];
                    }
                    ret[row][col] = curr;
                }

            }
        }
        int ans = 0;
        for (int col = 0; col < cols; col++) {
            ans += ret[rows-1][col];
        }
        return ans;
    }
}

class Solution115a {
    public int numDistinct(String s, String t) {
        int rows = t.length();
        int cols = s.length();
        int[][] ret = new int[rows+1][cols+1];
        for (int col = 0; col <= cols; col++) {
            ret[0][col] = 1;
        }

        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {

                if (s.charAt(col-1) == t.charAt(row-1)) {
                    ret[row][col] = ret[row-1][col-1] + ret[row][col-1];
                } else {
                    ret[row][col] = ret[row][col-1];
                }

            }
        }
        return ret[rows][cols];
    }
}
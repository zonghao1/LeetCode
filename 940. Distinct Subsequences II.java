/*
Given a string s, return the number of distinct non-empty subsequences of s. Since the answer may be very large, return it modulo 109 + 7.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not.


Example 1:

Input: s = "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
Example 2:

Input: s = "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and "abc".
Example 3:

Input: s = "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".


Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters.
 */

import java.util.Arrays;

class Solution940 {
    public int distinctSubseqII(String S) {
        int n = S.length(), M = (int)1e9 + 7, result = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (S.charAt(j) != S.charAt(i)) {
                    dp[i] += dp[j];
                    dp[i] %= M;
                }
            }
            result += dp[i];
            result %= M;
        }
        return result;
    }
}

class Solution940a {
    public int distinctSubseqII(String S) {
        int n = S.length(), M = (int)1e9 + 7;
        int[] count = new int[26];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int index = S.charAt(i) - 'a';
            int curr = (1 + sum - count[index] + M) % M;
            sum = (sum + curr) % M;
            count[index] = (count[index] + curr) % M;
        }
        return sum;
    }
}

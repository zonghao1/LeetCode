/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input: s = "aab", p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input: s = "mississippi", p = "mis*is*p*."
Output: false


Constraints:

0 <= s.length <= 20
0 <= p.length <= 30
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */

class Solution10 {
    public boolean isMatch(String s, String p) {
        if (p == null || p.length() == 0) {
            return s.length() == 0;
        }
        return dfs(s, 0, p, 0);
    }

    boolean dfs(String s, int sIndex, String p, int pIndex) {
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }

        if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
            if (dfs(s, sIndex, p, pIndex + 2)) {
                return true;
            }

            if (sIndex < s.length() && (p.charAt(pIndex) == '.' || p.charAt(pIndex) == s.charAt(sIndex))) {
                return dfs(s, sIndex + 1, p, pIndex);
            }
            return false;
        }

        else if (sIndex < s.length() && (p.charAt(pIndex) == '.' || p.charAt(pIndex) == s.charAt(sIndex))) {
            return dfs(s, sIndex + 1, p, pIndex + 1);
        }
        return false;
    }
}

class Solution10a {
    public boolean isMatch(String s, String p) {
        int rows = p.length() + 1;
        int cols = s.length() + 1;
        boolean[][] ret = new boolean[rows][cols];
        ret[0][0] = true;
        for (int row = 1; row < rows; row++) {
            if (p.charAt(row - 1) == '*') {
                ret[row][0] = ret[row-2][0];
            }
        }

        for (int col = 1; col < cols; col++) {
            for (int row = 1; row < rows; row++) {
                if (p.charAt(row - 1) == '.' || p.charAt(row - 1) == s.charAt(col - 1)) {
                    ret[row][col] |= ret[row-1][col-1];
                }
                if (p.charAt(row - 1) == '*') {
                    ret[row][col] |= ret[row-2][col];
                    if (p.charAt(row - 2) == '.' || p.charAt(row - 2) == s.charAt(col - 1)) {
                        /*
                        abcd
                        abcd*
                        如果上面去掉最后一个和下面match，并且上面最后一个和下面之前一个match
                         */
                        ret[row][col] |= ret[row][col-1];
                    }


                }
            }

        }
        return ret[ret.length - 1][ret[0].length - 1];
    }
}
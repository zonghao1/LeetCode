/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 */

import java.util.ArrayList;
import java.util.List;

class Solution22 {
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (n <= 0) {
            return ret;
        }
        dfs(ret, sb, 0,0,n);
        return ret;
    }

    void dfs(List<String> ret, StringBuilder sb, int left, int right, int n) {
        if (right == n && left == n) {
            ret.add(sb.toString());
            return;
        }
        if (left < n) {
            sb.append('(');
            dfs(ret, sb, left + 1, right, n);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (right < left) {
            sb.append(')');
            dfs(ret, sb, left, right + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }

    }


}
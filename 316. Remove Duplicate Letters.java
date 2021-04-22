//Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
//
//        Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
//
//
//
//        Example 1:
//
//        Input: s = "bcabc"
//        Output: "abc"
//        Example 2:
//
//        Input: s = "cbacdcbc"
//        Output: "acdb"

import java.util.ArrayDeque;
import java.util.Deque;

class Solution316 {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        Deque<Character> stack = new ArrayDeque<>();
        int[] remain = new int[26];
        boolean[] visited = new boolean[26];

        for (char c: s.toCharArray()) {
            remain[c - 'a']++;
        }
        for (char c: s.toCharArray()) {
            remain[c - 'a']--;
            if (stack.isEmpty()) {
                stack.push(c);

                visited[c-'a'] = true;
            } else if (visited[c-'a']) {
                continue;
            } else {
                while (!stack.isEmpty() && stack.peek() > c && remain[stack.peek()-'a'] > 0) {
                    visited[stack.pop()-'a'] = false;
                }
                stack.push(c);
                visited[c-'a'] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
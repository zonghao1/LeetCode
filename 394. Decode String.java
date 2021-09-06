/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"


Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
 */

class Solution394 {

    // Recursive Solution, append "abc" then "123" then "[]" then recursive
    public String decodeString(String s) {
        return helper(s, 0, s.length() - 1);
    }

    String helper(String s, int start, int end) {
        if (start > end) return "";
        int repeat = 0;
        StringBuilder sb = new StringBuilder();

        while (start <= end && s.charAt(start) >= 'a' && s.charAt(start) <= 'z') {
            sb.append(s.charAt(start));
            start++;
        }

        while (start <= end && s.charAt(start) >= '0' && s.charAt(start) <= '9') {
            repeat = repeat * 10 + s.charAt(start) - '0';
            start++;
        }

        if (repeat > 0) {
            start++;
            int left = 1;
            int right = 0;
            int startIndex = start;
            while (start  <= end && left != right) {
                if (s.charAt(start) == '[') {
                    left++;
                } else if (s.charAt(start) ==']') {
                    right++;
                }
                start++;
            }
            int endIndex = start;
            String inside = helper(s, startIndex, endIndex-2);
            for (int i = 0; i < repeat;i++) {
                sb.append(inside);
            }
        }

        sb.append(helper(s, start, end));



        return sb.toString();


    }


}
//Given a string s, return the longest palindromic substring in s.
//
//
//
//        Example 1:
//
//        Input: s = "babad"
//        Output: "bab"
//        Note: "aba" is also a valid answer.
//        Example 2:
//
//        Input: s = "cbbd"
//        Output: "bb"
//        Example 3:
//
//        Input: s = "a"
//        Output: "a"
//        Example 4:
//
//        Input: s = "ac"
//        Output: "a"

class Solution5 {
    public String longestPalindrome(String s) {
        int[] ret = new int[]{0,0};
        for (int i = 0; i < s.length(); i++) {
            int[] single = getLength(s, i, i);
            if (single[1] > ret[1]) {
                ret = single;
            }
            int[] two = getLength(s, i, i + 1);
            if (two[1] > ret[1]) {
                ret = two;
            }
        }
        return s.substring(ret[0], ret[0] + ret[1]);
    }


    int[] getLength(String s, int l, int r) {
        int[] ret = new int[]{0, 0};
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
            int length = r - l - 1;
            if (length > ret[1]) {
                ret = new int[]{l + 1, length};
            }
        }
        return ret;

    }

}
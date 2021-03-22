//Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
//
//


class Solution680 {
    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length() - 1, 0);
    }

    boolean validPalindrome(String s, int i, int j, int skip) {
        if (skip > 1) {
            return false;
        }
        if (i > j) {
            return false;
        }
        if (i == j) {
            return true;
        }
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return validPalindrome(s, i + 1, j, skip + 1) || validPalindrome(s, i, j-1, skip + 1);
            }
        }
        return true;

    }
}
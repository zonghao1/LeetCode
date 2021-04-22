/*
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().



Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Example 3:

Input: haystack = "", needle = ""
Output: 0


Constraints:

0 <= haystack.length, needle.length <= 5 * 104
haystack and needle consist of only lower-case English characters.
 */

class Solution28 {
    public int strStr(String haystack, String needle) {
        String large = haystack;
        String small = needle;
        if (large.length() < small.length()) {
            return -1;
        }
        if (small.length() == 0) {
            return 0;
        }

        int largePrime = 101;
        int prime = 31;
        int seed = 1;
        int target = 0;
        int hash = 0;
        for (int i = 0; i < small.length(); i++) {
            target = doHash(target, small.charAt(i), prime, largePrime);
            if (i > 0) {
                seed = doHash(seed, 0, prime, largePrime);
            }
            hash = doHash(hash, large.charAt(i), prime, largePrime);
        }
        if (hash == target && equals(large, small, 0)) {
            return 0;
        }
        for (int i = 1; i + small.length() <= large.length(); i++) {
            hash = nonNegative(hash - seed * large.charAt(i - 1) % largePrime, largePrime);
            hash = doHash(hash, large.charAt(i + small.length() - 1), prime, largePrime);
            if (hash == target && equals(large, small, i)) {
                return i;
            }
        }
        return -1;


    }

    boolean equals(String large, String small, int start) {
        for (int i = 0; i < small.length(); i++) {
            if (large.charAt(i + start) != small.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    int doHash(int hash, int addOn, int prime, int largePrime) {
        return (hash * prime % largePrime + addOn) % largePrime;
    }

    int nonNegative(int hash, int largePrime) {
        if (hash < 0) {
            hash += largePrime;
        }
        return hash;
    }
}
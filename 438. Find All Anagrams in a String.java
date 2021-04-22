/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

import java.util.ArrayList;
import java.util.List;

class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        int[] counts = new int[26];
        int diffChars = 0;
        for (char c: p.toCharArray()) {
            counts[c-'a']++;
            if (counts[c-'a'] == 1) {
                diffChars++;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts[c-'a']--;
            if (counts[c-'a'] == 0) {
                diffChars--;
            }
            if (i >= p.length()) {
                char prev = s.charAt(i - p.length());
                counts[prev-'a']++;
                if (counts[prev-'a'] == 1) {
                    diffChars++;
                }
            }
            if (diffChars == 0) {
                ret.add(i - p.length() + 1);
            }
        }
        return ret;



    }
}
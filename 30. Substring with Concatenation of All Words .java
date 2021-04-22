/*
You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.

You can return the answer in any order.



Example 1:

Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
Example 3:

Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]


Constraints:

1 <= s.length <= 104
s consists of lower-case English letters.
1 <= words.length <= 5000
1 <= words[i].length <= 30
words[i] consists of lower-case English letters.

 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return ret;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int wordLength = words[0].length();
        int length = words.length;

        for (int i = 0; i < s.length() - wordLength * length + 1; i++) {
            int j = i;
            int times = 0;
            Map<String, Integer> copy = new HashMap<>(map);
            while (times < length) {
                String curr = s.substring(j, j + wordLength);
                if (!copy.containsKey(curr) || copy.getOrDefault(curr, 0) < 1) {
                    break;
                }
                copy.put(curr, copy.get(curr) - 1);
                j += wordLength;
                times ++;
            }
            if (times == length) {
                ret.add(i);
            }

        }
        return ret;

    }
}
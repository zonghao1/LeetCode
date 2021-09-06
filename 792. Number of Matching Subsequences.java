/*
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2


Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.
 */

import java.util.ArrayList;
import java.util.List;

class Solution782 {
    public int numMatchingSubseq(String s, String[] words) {
        int ret = 0;
        List<Node>[] buckets = new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (String word: words) {
            int index = word.charAt(0) -'a';
            buckets[index].add(new Node(word, 0));
        }

        for (char c: s.toCharArray()) {
            List<Node> bucket = buckets[c-'a'];
            buckets[c-'a'] = new ArrayList<>();

            for (Node node: bucket) {

                if (node.index == node.word.length() - 1) {
                    ret++;
                } else {
                    node.index++;

                    int currChar = node.word.charAt(node.index);
                    buckets[currChar-'a'].add(node);
                }
            }
        }
        return ret;


    }

    class Node {
        String word;
        int index;
        Node(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }


}
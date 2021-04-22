/*
Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.



Example 1:

Input: words = ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]]
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: words = ["bat","tab","cat"]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["battab","tabbat"]
Example 3:

Input: words = ["a",""]
Output: [[0,1],[1,0]]


Constraints:

1 <= words.length <= 5000
0 <= words[i].length <= 300
words[i] consists of lower-case English letters.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution336 {

    class Node {
        Node[] next;
        List<Integer> valid;
        int index;
        Node() {
            next = new Node[26];
            valid = new ArrayList<>();
            index = -1;
        }
    }


    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();
        Node root = new Node();
        for (int i = 0; i < words.length; i++) {
            char[] curr = words[i].toCharArray();
            Node currNode = root;

            for (int right = curr.length - 1; right >= 0; right--) {
                int index = curr[right] - 'a';
                if (currNode.next[index] == null) {
                    currNode.next[index] = new Node();
                }
                if (isPalindrome(curr, 0, right)) {
                    currNode.valid.add(i);
                }
                currNode = currNode.next[index];
            }
            currNode.valid.add(i);
            currNode.index = i;
        }

        for (int i = 0; i < words.length; i++) {
            Node curr = root;
            for (int j = 0; j < words[i].length(); j++) {

                if (curr.index >= 0 && curr.index != i && isPalindrome(words[i].toCharArray(), j, words[i].length() - 1)) {
                    ret.add(Arrays.asList(i, curr.index));
                }

                curr = curr.next[words[i].charAt(j) - 'a'];
                if (curr == null) break;
            }
            if (curr != null) {
                for (int j : curr.valid) {
                    if (i == j) continue;
                    ret.add(Arrays.asList(i, j));
                }
            }

        }
        return ret;
    }




    boolean isPalindrome(char[] word, int left, int right) {
        while (left < right) {
            if (word[left++] != word[right--]) {
                return false;
            }
        }
        return true;
    }

}
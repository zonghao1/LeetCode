/*
There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.



Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.
 */

import java.util.*;

class Solution269 {

    final int UNVISITED = 0, VISITING = 1, VISITED = 2;

    public String alienOrder(String[] words) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Node head = new Node();
        boolean[] occured = new boolean[26];
        for (int i = 0; i < words.length; i++) {
            String curr = words[i];
            Node currNode = head;
            for (int j = 0; j < curr.length(); j++) {
                int index = curr.charAt(j) - 'a';
                occured[index] = true;
                for (int k = 0; k < 26; k++) {
                    if (k == index) {
                        continue;
                    }
                    if(currNode.laterNodes[k] != null) {
                        if (map.get(k) == null) {
                            map.put(k, new HashSet<>());
                        }
                        map.get(k).add(index);
                    }
                }
                if (currNode.laterNodes[index] == null) {
                    currNode.laterNodes[index] = new Node();
                }
                currNode = currNode.laterNodes[index];
                if (j == curr.length() - 1) {
                    for (int p = 0; p < 26; p++) {
                        if (currNode.laterNodes[p] != null) {
                            return "";
                        }
                    }
                }
            }
        }
        /*
        words: ["wrt","wrf","er","ett","rftt"]
        map: {17:19,19:5,4:17,22:17}
        */
        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add(map.getOrDefault(i, new HashSet<>()));
        }

        int[] status = new int[26];
        List<Integer> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (!dfs(sb, list, i, status, occured)) {
                return "";
            }
        }



        // for (int i = ret.size() - 1; i >= 0; i--) {
        //     sb.append((char) (ret.get(i) + 'a'));
        // }
        return sb.reverse().toString();

    }

    boolean dfs(StringBuilder sb, List<Set<Integer>> list, int index, int[] status, boolean[] occur) {
        if (!occur[index]) {
            return true;
        }
        if (status[index] == VISITED) {
            return true;
        }
        if (status[index] == VISITING) {
            return false;
        }
        Set<Integer> followers = list.get(index);
        status[index] = VISITING;
        for (Integer follower: followers) {
            if (!dfs(sb, list, follower, status, occur)) {
                return false;
            }
        }
        status[index] = VISITED;
        sb.append((char) (index + 'a'));
        return true;


    }




    class Node {
        Node[] laterNodes = new Node[26];
    }

}
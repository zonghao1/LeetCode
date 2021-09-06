/*
Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []


Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */

import java.util.*;

class Solution140a {

    //继续用DP做法，但是不是只记录True/False, 而是记录所有的可能combo
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < wordDict.size(); i++) {
            map.put(wordDict.get(i), i);
        }
        List<List<List<Integer>>> list = new ArrayList<>();

        list.add(new ArrayList<>());
        list.get(0).add(new ArrayList<>());
        list.get(0).get(0).add(-1);

        for (int end = 1; end <= s.length(); end++) {
            List<List<Integer>> curr = new ArrayList<>();

            for (int start = 0; start < end; start++) {
                if (list.get(start).size() != 0 && map.containsKey(s.substring(start, end))) {
                    for (List<Integer> currList: list.get(start)) {
                        List<Integer> addList = new ArrayList<>(currList);
                        addList.add(map.get(s.substring(start, end)));
                        curr.add(addList);
                    }
                }

            }
            list.add(curr);
        }

        List<String> ret = new ArrayList<>();
        List<List<Integer>> lastList = list.get(list.size() - 1);
        for (List<Integer> printList: lastList) {
            StringBuilder sb = new StringBuilder();
            for (Integer i: printList) {
                if (i == -1) {
                    continue;
                }
                sb.append(wordDict.get(i));
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            ret.add(sb.toString());
        }
        return ret;


    }
}

class Solution140b {

    // Recursion, 因为第一段必须用到结果里

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ret = new ArrayList<>();
        Set<String> set = new HashSet<>(wordDict);
        Map<String, List<String>> map = new HashMap<>();
        return helper(set, map, s);
    }

    List<String> helper(Set<String> set, Map<String, List<String>> map, String s) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        if (s.length() == 0) {
            return null;
        }
        List<String> curr = new ArrayList<>();

        for (int end = 1; end <= s.length(); end++) {
            String firstPart = s.substring(0, end);
            if (set.contains(firstPart)) {
                List<String> laterList = helper(set, map, s.substring(end));
                if (laterList == null) {
                    curr.add(firstPart);
                    return curr;
                } else {
                    for (String sb: laterList) {
                        curr.add(firstPart + " " + sb);
                    }
                }
            }
        }
        map.put(s, curr);
        return curr;
    }
}
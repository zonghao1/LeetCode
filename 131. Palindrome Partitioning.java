/*
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.



Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]


Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 */

import java.util.ArrayList;
import java.util.List;

class Solution131 {
    public List<List<String>> partition(String s) {
        List<List<List<String>>> ret = new ArrayList<>();
        List<String> start = new ArrayList<>();
        List<List<String>> startList = new ArrayList<>();
        startList.add(start);
        ret.add(startList);


        for (int i = 0; i < s.length(); i++) {
            List<List<String>> curr = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (valid(s, j, i)) {
                    List<List<String>> prev = ret.get(j);
                    for (List<String> list: prev) {
                        List<String> newList = new ArrayList<>(list);
                        newList.add(s.substring(j, i + 1));
                        curr.add(newList);
                    }
                }


            }
            ret.add(curr);


        }
        return ret.get(ret.size() - 1);




    }


    boolean valid(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }



}
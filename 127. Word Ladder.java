/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.



Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.


Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
 */

import java.util.*;

class Solution127 {
    // 提前处理每个word差一位的关系
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Map<String, Set<String>> map = new HashMap<>();
        wordList.add(beginWord);
        for (int i = 0; i < wordList.size(); i++) {
            map.put(wordList.get(i), new HashSet<>());
            for (int j = 0; j < wordList.size(); j++) {
                if (diff(wordList.get(i), wordList.get(j)) == 1) {
                    map.get(wordList.get(i)).add(wordList.get(j));
                }
            }
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        Set<String> used = new HashSet<>();
        used.add(beginWord);

        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(endWord)) {
                    return steps;
                }
                used.add(curr);

                Set<String> neighbors = map.get(curr);
                for (String neighbor: neighbors) {
                    if (!used.contains(neighbor)) {
                        queue.offer(neighbor);
                    }

                }


            }


        }
        return 0;
        
    }

    int diff (String a, String b) {
        int ret = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                ret++;
            }
        }
        return ret;
    }


}



class Solution127a {
    //单向BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int step = 0;

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char[] curr = queue.poll().toCharArray();
                for (int index = 0; index < curr.length; index++) {
                    char c = curr[index];
                    for (int j = 0; j < 26; j++) {
                        char newChar = (char) (j - 0 + 'a');
                        if (newChar == c) continue;
                        curr[index] = newChar;
                        String newString = new String(curr);
                        if (newString.equals(endWord)) return step + 1;
                        if (!set.contains(newString)) continue;
                        queue.offer(newString);
                        set.remove(newString);
                    }
                    curr[index] = c;
                }
            }
        }
        return 0;
    }
}

class Solution127b {
    // 双向BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;
        int step = 0;
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add(beginWord);
        set2.add(endWord);

        while (!set1.isEmpty() && !set2.isEmpty()) {
            if (set1.size() > set2.size()) {
                Set<String> temp = set1;
                set1 = set2;
                set2 = temp;
            }
            Set<String> newSet = new HashSet<>();
            step++;
            for (String s: set1) {
                char[] array = s.toCharArray();
                for (int i = 0; i < array.length; i++) {
                    char c = array[i];
                    for (char newChar = 'a'; newChar <= 'z'; newChar++) {
                        array[i] = newChar;
                        if (newChar == c) continue;
                        String newString = new String(array);
                        if (set2.contains(newString)) return step + 1;
                        if (!set.contains(newString)) continue;
                        newSet.add(newString);
                        set.remove(newString);
                    }
                    array[i] = c;

                }


            }
            set1 = newSet;

        }
        return 0;
    }
}
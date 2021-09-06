/*
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.



Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"


Constraints:

1 <= s.length <= 105
2 <= k <= 104
s only contains lower case English letters.
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Solution1209 {
    public String removeDuplicates(String s, int k) {
        Deque<Character> deque = new ArrayDeque<>();
        Deque<Integer> count = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (deque.isEmpty() || s.charAt(i) != deque.peekLast()) {
                deque.offerLast(s.charAt(i));
                count.offerLast(1);
            } else {
                int newCount = count.pollLast() + 1;
                if (newCount == k) {
                    deque.pollLast();
                } else {
                    count.offerLast(newCount);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int size = deque.size();
        for (int i = 0; i < size; i++) {
            int currCount = count.pollFirst();
            char c = deque.pollFirst();
            for (int j = 0; j < currCount; j++) {
                sb.append(c);
            }
        }
        return sb.toString();

    }
}

class Solution1209b {
    public String removeDuplicates(String s, int k) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] array = s.toCharArray();
        int[] count = new int[array.length];
        int slow = 0;
        int countIndex = -1;

        for (int fast = 0; fast < array.length; fast++) {
            if (slow == 0 || array[fast] != array[slow-1]) {
                array[slow++] = array[fast];
                count[countIndex+1] = 1;
                countIndex++;
            } else {
                count[countIndex]++;
                if (count[countIndex] == k) {
                    countIndex--;
                    slow -= k - 1;
                } else {
                    array[slow++] = array[fast];
                }


            }

        }

        return new String(array, 0, slow);
    }
}
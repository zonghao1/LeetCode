/*
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 */

import java.util.*;

class Solution692 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<String> ret = new ArrayList<>();
        if (words == null || words.length == 0 || k == 0) {
            return ret;
        }
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                if (!a.getValue().equals(b.getValue())) {
                    return a.getValue().compareTo(b.getValue());
                } else {
                    return -(a.getKey().compareTo(b.getKey()));
                }
            }
        });


        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                pq.offer(entry);
            } else {
                if (pq.peek().getValue() < entry.getValue() || (pq.peek().getValue().equals(entry.getValue()) && pq.peek().getKey().compareTo(entry.getKey()) > 0)) {
                    pq.poll();
                    pq.offer(entry);
                }
            }
        }
        while (!pq.isEmpty()) {
            ret.add(0, pq.poll().getKey());
        }
        return ret;

    }
}

class Solution692a {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<String> ret = new ArrayList<>();
        if (words == null || words.length == 0 || k == 0) {
            return ret;
        }
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        quickSelect(list, 0, list.size() - 1, k);

        List<Map.Entry<String, Integer>> newList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            newList.add(list.get(i));
        }

        Collections.sort(newList, new MyComparator());
        for (int i = 0; i < k; i++) {
            ret.add(0, newList.get(i).getKey());
        }
        return ret;

    }

    void quickSelect(List<Map.Entry<String, Integer>> list, int left, int right, int k) {
        int mid = partition(list, left, right, k);
        if (mid == k - 1) {
            return;
        }
        if (mid < k) {
            quickSelect(list, mid + 1, right, k);
        } else {
            quickSelect(list, left, mid - 1, k);
        }
    }

    int partition(List<Map.Entry<String, Integer>> list, int left, int right, int k) {
        Map.Entry<String, Integer> pivot = list.get(right);
        int end = right;
        right--;
        while (left <= right) {
            if (list.get(left).getValue() > pivot.getValue() || (list.get(left).getValue().equals(pivot.getValue()) && (list.get(left).getKey().compareTo(pivot.getKey()) < 0))) {
                left++;
            } else if (list.get(right).getValue() < pivot.getValue() || (list.get(right).getValue().equals(pivot.getValue()) && (list.get(right).getKey().compareTo(pivot.getKey()) > 0))) {
                right--;
            } else {
                swap(list, left++, right--);
            }
        }
        swap(list, left, end);
        return left;


    }


    void swap(List<Map.Entry<String, Integer>> list, int i, int j) {
        Map.Entry<String, Integer> temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    class MyComparator implements Comparator<Map.Entry<String, Integer>> {
        public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
            if (a.getValue() != b.getValue()) {
                return a.getValue().compareTo(b.getValue());
            } else {
                return -(a.getKey().compareTo(b.getKey()));
            }
        }
    }


}
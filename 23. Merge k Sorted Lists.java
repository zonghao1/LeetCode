//
//You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//
//        Merge all the linked-lists into one sorted linked-list and return it.
//
//
//
//        Example 1:
//
//        Input: lists = [[1,4,5],[1,3,4],[2,6]]
//        Output: [1,1,2,3,4,4,5,6]
//        Explanation: The linked-lists are:
//        [
//        1->4->5,
//        1->3->4,
//        2->6
//        ]
//        merging them into one sorted list:
//        1->1->2->3->4->4->5->6
//        Example 2:
//
//        Input: lists = []
//        Output: []

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        return helper(lists, 0, lists.length - 1);
    }

    ListNode helper(ListNode[] lists, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return lists[l];
        }
        int mid = l + (r - l) / 2;
        ListNode first = helper(lists, l, mid);
        ListNode second = helper(lists, mid+1, r);
        return mergeTwo(first, second);
    }

    
    ListNode mergeTwo(ListNode a, ListNode b) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        while (a != null && b != null) {
            if (a.val < b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
            curr.next = null;
        }
        if (a != null) {
            curr.next = a;
        } else {
            curr.next = b;
        }
        return dummyHead.next;
    }


}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution23a {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            public int compare(ListNode a, ListNode b) {
                if (a.val == b.val) {
                    return 0;
                }
                return a.val < b.val? -1 : 1;
            }
        });
        ListNode curr = dummyHead;
        for (ListNode node: lists) {
            if (node != null) {
                pq.offer(node);}
        }

        while (!pq.isEmpty()) {
            ListNode thisNode = pq.poll();
            ListNode next = thisNode.next;
            curr.next = thisNode;
            curr = curr.next;
            curr.next = null;
            if (next != null) {
                pq.offer(next);
            }
        }
        return dummyHead.next;
    }
}
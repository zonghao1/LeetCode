//Given a linked list, swap every two adjacent nodes and return its head.
//
//
//
//        Example 1:
//
//
//        Input: head = [1,2,3,4]
//        Output: [2,1,4,3]
//        Example 2:
//
//        Input: head = []
//        Output: []
//        Example 3:
//
//        Input: head = [1]
//        Output: [1]

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
class Solution24 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        ListNode prev = head;
        while (head != null && head.next != null) {
            ListNode next = head.next;
            head.next = head.next.next;
            next.next = head;
            if (next != newHead) {
                prev.next = next;
                prev = head;
            }
            head = head.next;

        }
        return newHead;
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
class Solution24a {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode nextPart = swapPairs(head.next.next);
        ListNode next = head.next;
        head.next.next = head;
        head.next = nextPart;
        return next;

    }
}
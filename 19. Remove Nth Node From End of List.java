//Given the head of a linked list, remove the nth node from the end of the list and return its head.
//
//        Follow up: Could you do this in one pass?
//
//
//
//        Example 1:
//
//
//        Input: head = [1,2,3,4,5], n = 2
//        Output: [1,2,3,5]
//        Example 2:
//
//        Input: head = [1], n = 1
//        Output: []
//        Example 3:
//
//        Input: head = [1,2], n = 1
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
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        length -= (n);
        ListNode prev = dummyHead;
        while (length > 0) {
            prev = prev.next;
            length--;
        }

        prev.next = prev.next.next;
        return dummyHead.next;

    }
}
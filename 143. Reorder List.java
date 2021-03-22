//You are given the head of a singly linked-list. The list can be represented as:
//
//        L0 → L1 → … → Ln - 1 → Ln
//        Reorder the list to be on the following form:
//
//        L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
//        You may not modify the values in the list's nodes. Only nodes themselves may be changed.
//
//
//
//        Example 1:
//
//
//        Input: head = [1,2,3,4]
//        Output: [1,4,2,3]


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
class Solution143 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode mid = findMid(head);
        ListNode second = mid.next;
        mid.next = null;
        ListNode l2 = reverse(second);
        ListNode l1 = head;

        ListNode dummyHead = new ListNode(Integer.MAX_VALUE);
        ListNode curr = dummyHead;
        while (l1 != null && l2 != null) {
            curr.next = l1;
            curr = curr.next;
            l1 = l1.next;
            curr.next = l2;
            curr = curr.next;
            l2 = l2.next;
        }
        curr.next = l1;
        head = dummyHead.next;
    }





    ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }







    ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
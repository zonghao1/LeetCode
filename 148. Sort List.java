//Given the head of a linked list, return the list after sorting it in ascending order.
//
//        Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
//
//
//
//        Example 1:
//
//
//        Input: head = [4,2,1,3]
//        Output: [1,2,3,4]
//        Example 2:
//
//
//        Input: head = [-1,5,3,4,0]
//        Output: [-1,0,3,4,5]
//        Example 3:
//
//        Input: head = []
//        Output: []

///**
// * Definition for singly-linked list.
// * public class ListNode {
// *     int val;
// *     ListNode next;
// *     ListNode() {}
// *     ListNode(int val) { this.val = val; }
// *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// * }
// */
class Solution148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);
        ListNode midNext = mid.next;
        mid.next = null;
        ListNode first = sortList(head);
        ListNode second = sortList(midNext);
        return merge(first, second);
    }

    ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode curr = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
                curr = curr.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
                curr = curr.next;
            }
        }

        if (l1 != null) {
            curr.next = l1;
        } else if (l2 != null) {
            curr.next = l2;
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

// Bottom up merge
class Solution148a {
    public ListNode sortList(ListNode head) {
        int length = findLength(head);
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        for (int step = 1; step < length; step *= 2) {
            ListNode curr = dummyHead;
            while (curr != null && curr.next != null) {

                ListNode first = splitUp(curr.next, step);
                ListNode secondStart = first.next;
                ListNode second = splitUp(secondStart, step);
                ListNode nextPart = second == null? null: second.next;
                first.next = null;
                if (second != null) {
                    second.next = null;
                }
                ListNode[] pair = merge(curr.next, secondStart);
                curr.next = pair[0];
                pair[1].next = nextPart;
                curr = pair[1];
            }
        }
        return dummyHead.next;

    }

    ListNode[] merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode curr = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        ListNode[] ret = new ListNode[2];
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        ret[0] = dummyHead.next;
        while (curr != null && curr.next != null) {
            curr = curr.next;
        }
        ret[1] = curr;
        return ret;
    }



    int findLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }

    ListNode splitUp(ListNode head, int length) {
        ListNode curr = head;
        while (--length > 0 && curr != null && curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }


}
//
//You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//
//        You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//        Follow up:
//        What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
//
//        Example:
//
//        Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//        Output: 7 -> 8 -> 0 -> 7

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
class Solution445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int oneLength = getLength(l1);
        int twoLength = getLength(l2);
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode curr = dummyHead;
        ListNode one = l1;
        ListNode two = l2;
        for (int i = Math.max(oneLength, twoLength); i > 0; i--) {
            int oneValue = 0;
            int twoValue = 0;
            if (i <= oneLength) {
                oneValue = one.val;
                one = one.next;
            }
            if (i <= twoLength) {
                twoValue = two.val;
                two = two.next;
            }

            ListNode thisNode = new ListNode(oneValue + twoValue);
            curr.next = thisNode;
            curr = curr.next;
        }

        int first = helper(dummyHead.next);
        if (first == 1) {
            dummyHead.val = 1;
            return dummyHead;
        } else {
            return dummyHead.next;
        }


    }


    int helper(ListNode node) {
        if (node == null) {
            return 0;
        }
        int carryOn = helper(node.next);
        int value = node.val + carryOn;
        node.val = value % 10;
        return value / 10;
    }










    int getLength(ListNode l1) {
        int length = 0;
        while (l1 != null) {
            length++;
            l1 = l1.next;
        }
        return length;
    }



}
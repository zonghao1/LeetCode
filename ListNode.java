
  public class ListNode {

      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class ParentNode {
      public int val;
      public ParentNode left;
      public ParentNode right;
      public ParentNode parent;
  };
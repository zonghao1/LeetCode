//You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
//
//        Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
//
//
//
//        Example 1:
//
//
//        Input: root = [1,3,null,null,2]
//        Output: [3,1,null,null,2]
//        Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
//        Example 2:
//
//
//        Input: root = [3,1,4,null,null,2]
//        Output: [2,1,4,null,null,3]
//        Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution99 {

    public void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        boolean firstTime = true;
        TreeNode prev = new TreeNode(Integer.MIN_VALUE);
        while (root != null) {
            if (root.left == null) {
                if (prev.val > root.val && firstTime) {
                    first = prev;
                    firstTime = false;
                }
                if (prev.val > root.val && !firstTime) {
                    second = root;
                }
                prev = root;
                root = root.right;
            } else {
                TreeNode temp = root.left;
                while (temp.right != null && temp.right != root) {
                    temp = temp.right;
                }
                if (temp.right == null) {
                    temp.right = root;
                    root = root.left; //TODO
                } else {
                    temp.right = null;
                    if (prev.val > root.val && firstTime) {
                        first = prev;
                        firstTime = false;
                    }
                    if (prev.val > root.val && !firstTime) {
                        second = root;
                    }
                    prev = root;
                    root = root.right;
                }
            }


        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
//Given the root of a binary tree, return the postorder traversal of its nodes' values.
//
//
//
//        Example 1:
//
//
//        Input: root = [1,null,2,3]
//        Output: [3,2,1]
//        Example 2:
//
//        Input: root = []
//        Output: []
//        Example 3:
//
//        Input: root = [1]
//        Output: [1]

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        TreeNode prev = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) {
            return ret;
        }
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            if (prev == null || curr == prev.left || curr == prev.right) {
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    stack.pop();
                    ret.add(curr.val);
                }
            } else {
                if (prev == curr.right || (prev == curr.left && curr.right == null)) {
                    stack.pop();
                    ret.add(curr.val);
                } else {
                    stack.push(curr.right);
                }
            }
            prev = curr;
        }
        return ret;

    }
}
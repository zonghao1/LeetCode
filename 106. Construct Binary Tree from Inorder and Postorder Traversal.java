/*
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.



Example 1:


Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: inorder = [-1], postorder = [-1]
Output: [-1]


Constraints:

1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
 */

import java.util.HashMap;
import java.util.Map;

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
class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(map, inorder, 0, inorder.length - 1, postorder, 0, postorder.length -1);
    }

    TreeNode helper(Map<Integer, Integer> map, int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight) {
        if (postLeft > postRight) return null;
        TreeNode curr = new TreeNode(postorder[postRight]);
        int index = map.get(curr.val);
        int leftLength = index - inLeft;
        int rightLength = inRight - index;
        curr.right = helper(map, inorder, index + 1, inRight, postorder, postRight - rightLength, postRight - 1);
        curr.left = helper(map, inorder, inLeft, index - 1, postorder, postLeft, postRight - rightLength - 1 );
        return curr;

    }




}
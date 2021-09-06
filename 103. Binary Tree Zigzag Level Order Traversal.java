/*
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 */

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
class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        int level = 1;
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return ret;
        queue.offerFirst(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {

                if (level % 2 == 0) {
                    TreeNode curr = queue.pollLast();
                    list.add(curr.val);
                    if (curr.right != null) queue.offerFirst(curr.right);
                    if (curr.left != null) queue.offerFirst(curr.left);

                } else {
                    TreeNode curr = queue.pollFirst();
                    list.add(curr.val);
                    if (curr.left != null) queue.offerLast(curr.left);
                    if (curr.right != null) queue.offerLast(curr.right);

                }


            }
            ret.add(list);
            level++;
        }
        return ret;



    }
}
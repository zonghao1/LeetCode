/*
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.



Example 1:


Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
Example 2:

Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]


Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.

 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
class Solution1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ret = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i: to_delete) set.add(i);
        helper(ret, set, root, true);
        return ret;
    }

    TreeNode helper(List<TreeNode> ret, Set<Integer> set, TreeNode root, boolean isRoot) {
        if (root == null) return root;
        boolean toDelete = set.contains(root.val);
        if (isRoot & !toDelete) ret.add(root);
        root.left = helper(ret, set, root.left, toDelete);
        root.right = helper(ret, set, root.right, toDelete);
        return toDelete? null: root;
    }


}
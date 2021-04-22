//Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
//
//
//
//        Example 1:
//
//
//        Input: n = 3
//        Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
//        Example 2:
//
//        Input: n = 1
//        Output: [[1]]

import java.util.ArrayList;
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
class Solution95 {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ret = new ArrayList<>();
        if (n < 1) {
            return ret;
        }

        return helper(1, n);
    }

    List<TreeNode> helper(int left, int right) {
        List<TreeNode> ret = new ArrayList<>();
        if (left > right) {
            ret.add(null);

            return ret;
        }

        for (int curr = left; curr <= right; curr++) {
            List<TreeNode> leftPart = helper(left, curr - 1);
            List<TreeNode> rightPart = helper(curr + 1, right);
            for (TreeNode leftNode: leftPart) {
                for (TreeNode rightNode: rightPart) {
                    TreeNode newCurr = new TreeNode(curr, leftNode, rightNode);
                    ret.add(newCurr);
                }
            }
        }
        return ret;
    }

}
//Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
//
//
//
//        Example 1:
//
//
//        Input: root = [3,9,20,null,null,15,7]
//        Output: [[15,7],[9,20],[3]]
//        Example 2:
//
//        Input: root = [1]
//        Output: [[1]]
//        Example 3:
//
//        Input: root = []
//        Output: []

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
class Solution107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        dfs(ret, root, 1);
        return ret;

    }

    void dfs(List<List<Integer>> ret, TreeNode root, int height) {
        if (root == null) {
            return;
        }
        if (ret.size() < height) {
            ret.add(0, new ArrayList<>());
        }
        List<Integer> currList = ret.get(ret.size() - height);
        currList.add(root.val);
        dfs(ret, root.left, height+1);
        dfs(ret, root.right, height+1);
    }




}
/*
Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).



Example 1:


Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.
Example 2:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3


Constraints:

The number of nodes in the tree is in the range [0, 1000].
-109 <= Node.val <= 109
-1000 <= targetSum <= 1000
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
class Solution437 {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        return helper(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    int helper(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int ret = 0;
        if (root.val == targetSum) {
            ret++;
        }
        return ret + helper(root.left, targetSum - root.val) + helper(root.right, targetSum - root.val);
    }
}


class Solution437a {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return helper(root, targetSum, map, 0);
    }

    int helper(TreeNode root, int targetSum, Map<Integer, Integer> map, int currSum) {
        if (root == null) return 0;
        currSum += root.val;
        int ret = 0;
        ret += map.getOrDefault(currSum - targetSum, 0);
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        ret += helper(root.left, targetSum, map, currSum);
        ret += helper(root.right, targetSum, map, currSum);
        map.put(currSum, map.get(currSum) - 1);
        return ret;

    }

}
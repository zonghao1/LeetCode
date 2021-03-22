//Given the root of a binary tree, determine if it is a complete binary tree.
//
//        In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
//
//
//
//        Example 1:
//
//
//        Input: root = [1,2,3,4,5,6]
//        Output: true
//        Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
//        Example 2:
//
//
//        Input: root = [1,2,3,4,5,null,7]
//        Output: false
//        Explanation: The node with value 7 isn't as far left as possible.


import java.util.LinkedList;
import java.util.Queue;

class Solution958 {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        boolean flag = false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (flag) {
                if(curr.left != null || curr.right != null) {
                    return false;
                }
            } else {
                if (curr.left == null && curr.right != null) {
                    return false;
                }
                if (curr.left == null) {
                    flag = true;
                } else {
                    queue.offer(curr.left);
                }
                if (curr.right == null) {
                    flag = true;
                } else {
                    queue.offer(curr.right);
                }
            }

        }
        return true;
    }
}

class Solution958a {
    private int target_height = 0, last_level_filled = 0;
    private boolean dfs(TreeNode r, int h) {
        if (r == null) {
            if (target_height == 0) {
                target_height = h;
            } else if (h == target_height - 1) {
                last_level_filled = 1;
            }
            return h == target_height - last_level_filled;
        }
        return dfs(r.left, h + 1) && dfs(r.right, h + 1);
    }
    public boolean isCompleteTree(TreeNode root) {
        return dfs(root, 0);
    }


}

class Solution958b {

    public boolean isCompleteTree(TreeNode root) {
        int[] target_height = new int[1];
        int[] last_level_filled = new int[1];
        return dfs(root, 0, target_height, last_level_filled);
    }

    private boolean dfs(TreeNode r, int h, int[] target_height, int[] last_level_filled) {
        int i = 0;
        if (r == null) {
            if (target_height[0] == 0) {
                target_height[0] = h;
            } else if (h == target_height[0] - 1) {
                last_level_filled[0] = 1;
            }
            return h == target_height[0] - last_level_filled[0];
        }
        return dfs(r.left, h + 1, target_height, last_level_filled)
                && dfs(r.right, h + 1, target_height, last_level_filled);
    }

}
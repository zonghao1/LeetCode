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
class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        while (root != null) {
            if (root.left == null) {
                ret.add(root.val);
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
                    ret.add(root.val);
                    root = root.right;
                }
            }


        }
        return ret;
    }
}

//class Solution {
//    public List<Integer> inorderTraversal(TreeNode root) {
//        Stack<TreeNode> stack = new Stack<>();
//        List<Integer> ret = new ArrayList<>();
//        while (root != null || !stack.isEmpty()) {
//            if (root != null) {
//                stack.push(root);
//                root = root.left;
//            } else {
//                root = stack.pop();
//                ret.add(root.val);
//                root = root.right;
//            }
//        }
//        return ret;
//    }
//}
/*

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */

import java.util.ArrayList;
import java.util.List;

class Solution46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null || nums.length == 0) return ret;
        dfs(ret, nums, 0);
        return ret;

    }

    void dfs(List<List<Integer>> ret, int[] nums, int level) {
        if (level == nums.length - 1) {
            List<Integer> curr = new ArrayList<>();
            for (int i: nums) {
                curr.add(i);
            }
            ret.add(curr);
            return;
        }

        for (int i = level; i < nums.length; i++) {
            swap(nums, i, level);
            dfs(ret, nums, level + 1);
            swap(nums, i, level);
        }


    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



}
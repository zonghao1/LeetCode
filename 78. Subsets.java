/*
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 */

import java.util.ArrayList;
import java.util.List;

class Solution78a {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < (1 << nums.length); i++) {
            ret.add(helper(nums, i));
        }
        return ret;
    }

    List<Integer> helper(int[] nums, int number) {
        List<Integer> ret = new ArrayList<>();
        int index = 0;
        while (number > 0) {
            if ((number & 1) != 0) {
                ret.add(nums[index]);
            }
            index++;
            number >>= 1;
        }
        return ret;
    }

}

class Solution78b {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ret;
        }
        dfs(ret, new ArrayList<>(), nums, 0);
        return ret;
    }

    void dfs(List<List<Integer>> ret, List<Integer> curr, int[] nums, int level) {
        if (level == nums.length) {
            ret.add(new ArrayList<>(curr));
            return;
        }
        dfs(ret, curr, nums, level + 1);
        curr.add(nums[level]);
        dfs(ret, curr, nums, level + 1);
        curr.remove(curr.size() - 1);
    }


}
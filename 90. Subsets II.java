/*
Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null) {
            return ret;
        }
        Arrays.sort(nums);
        dfs(ret, new ArrayList<>(), nums, 0);
        return ret;
    }


    void dfs(List<List<Integer>> ret, List<Integer> curr, int[] nums, int level) {
        if (level == nums.length) {
            ret.add(new ArrayList<>(curr));
            return;
        }

        curr.add(nums[level]);
        dfs(ret, curr, nums, level + 1);
        curr.remove(curr.size() - 1);
        while (level + 1 < nums.length && nums[level + 1] == nums[level]) {
            level++;
        }
        dfs(ret, curr,  nums, level + 1);
    }

}

class Solution90a {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null) {
            return ret;
        }
        Arrays.sort(nums);
        dfs(ret, new ArrayList<>(), nums, 0);
        return ret;
    }


    void dfs(List<List<Integer>> ret, List<Integer> curr, int[] nums, int level) {
        ret.add(new ArrayList<>(curr));

        for (int i = level; i < nums.length; i++) {
            if (level == i || nums[i] != nums[i - 1]) {
                curr.add(nums[i]);
                dfs(ret, curr, nums, i + 1);
                curr.remove(curr.size() - 1);
            }
        }


    }

}
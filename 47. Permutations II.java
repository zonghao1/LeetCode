/*
Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.



Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */

import java.util.*;

class Solution47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null || nums.length <= 0) {
            return ret;
        }
        Arrays.sort(nums);
        dfs(ret, nums, 0);
        return ret;
    }

    /*
    0 0 1 2
    最后一次，是 2 0 1 0, 这样只用连续判断就不行了，只能用Set
     */

    void dfs(List<List<Integer>> ret, int[] nums, int level) {
        if (level == nums.length) {
            List<Integer> curr = new ArrayList<>();
            for (int i: nums) {
                curr.add(i);
            }
            ret.add(curr);
            return;
        }
        Set<Integer> set = new HashSet<>();

        for (int i = level; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                continue;
            }
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
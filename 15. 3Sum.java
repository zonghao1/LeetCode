//Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
//
//        Notice that the solution set must not contain duplicate triplets.
//
//
//
//        Example 1:
//
//        Input: nums = [-1,0,1,2,-1,-4]
//        Output: [[-1,-1,2],[-1,0,1]]
//        Example 2:
//
//        Input: nums = []
//        Output: []
//        Example 3:
//
//        Input: nums = [0]
//        Output: []

import java.util.*;

class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ret;
        }
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }

                int target = - (nums[i] + nums[j]);
                if (map.containsKey(target) && map.get(target) > j) {
                    List<Integer> curr = new ArrayList<>();
                    curr.add(nums[i]);
                    curr.add(nums[j]);
                    curr.add(target);
                    ret.add(curr);

                }
            }
        }
        return ret;


    }
}

class Solution15a {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ret;
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int start = i + 1;
            int end = nums.length - 1;
            int target = -nums[i];
            while (start < end) {
                if (start > i + 1 && nums[start] == nums[start - 1]) {
                    start++;
                    continue;
                }
                if (nums[start] + nums[end] == target) {
                    List<Integer> curr = Arrays.asList(nums[i], nums[start], nums[end]);
                    ret.add(curr);
                    start++;
                    end--;
                } else if (nums[start] + nums[end] < target) {
                    start++;
                } else {
                    end--;
                }
            }

        }
        return ret;
    }
}

class Solution15b {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ret;
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int target = -nums[i];
            Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {

                if (set.contains(target - nums[j])) {
                    ret.add(Arrays.asList(nums[i], nums[j], target - nums[j]));
                    while (j + 1< nums.length && nums[j+1] == nums[j]) {
                        j++;
                    }
                }

                set.add(nums[j]);
            }
        }
        return ret;


    }
}
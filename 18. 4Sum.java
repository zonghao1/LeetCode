//Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum
// of target.
//
//        Notice that the solution set must not contain duplicate quadruplets.
//
//
//
//        Example 1:
//
//        Input: nums = [1,0,-1,0,-2,2], target = 0
//        Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//        Example 2:
//
//        Input: nums = [], target = 0
//        Output: []

import java.util.*;

class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Map<Integer, List<Pair>> map = new HashMap<>();
        Arrays.sort(nums);
        if (nums == null || nums.length < 4) {
            return ret;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }

                int sum = nums[i] + nums[j];
                if (!map.containsKey(sum)) {
                    map.put(sum, new ArrayList<>());
                }
                map.get(sum).add(new Pair(i, j));
                System.out.println(i + " " + j);
            }
        }

        for (int i = nums.length - 2; i >= 0; i--) {

            // if (i < nums.length - 2 && nums[i] == nums[i+1]) {
            //         continue;
            //     }

            if (i < nums.length - 2 && nums[i] == nums[i+1] && nums[i+1] == nums[i+2]) {
                continue;
            }

            if (i < nums.length - 1 && nums[i] == nums[i+1]) {
                int sum = target -(nums[i] + nums[i]);
                if (map.containsKey(sum)) {
                    List<Pair> list = map.get(sum);
                    for (Pair pair: list) {
                        // System.out.println(i + " " + j + " " + pair.left + " " + pair.right);
                        if (pair.right < i) {
                            ret.add(Arrays.asList(nums[pair.left], nums[pair.right], nums[i], nums[i]));
                        }
                    }
                }
                continue;
            }


            for (int j = i + 1; j < nums.length; j++) {

                if (j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }

                int sum = target -(nums[i] + nums[j]);
                if (map.containsKey(sum)) {
                    List<Pair> list = map.get(sum);
                    for (Pair pair: list) {
                        // System.out.println(i + " " + j + " " + pair.left + " " + pair.right);
                        if (pair.right < i) {
                            ret.add(Arrays.asList(nums[pair.left], nums[pair.right], nums[i], nums[j]));
                        }
                    }
                }


            }

        }
        return ret;





    }


    class Pair {
        int left;
        int right;
        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

}

//class Solution {
//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        List<List<Integer>> ret = new ArrayList<>();
//        Map<Integer, List<Pair>> map = new HashMap<>();
//        Arrays.sort(nums);
//        if (nums == null || nums.length < 4) {
//            return ret;
//        }
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (i > 0 && nums[i] == nums[i-1]) {
//                continue;
//            }
//
//            for (int j = i + 1; j < nums.length; j++) {
//                if (j > i + 1 && nums[j] == nums[j-1]) {
//                    continue;
//                }
//
//                int sum = nums[i] + nums[j];
//                if (!map.containsKey(sum)) {
//                    map.put(sum, new ArrayList<>());
//                }
//                map.get(sum).add(new Pair(i, j));
//                // System.out.println(i + " " + j);
//            }
//        }
//
//        for (int i = nums.length - 2; i >= 0; i--) {
//
//            // if (i < nums.length - 2 && nums[i] == nums[i+1]) {
//            //         continue;
//            //     }
//
//            if (i < nums.length - 2 && nums[i] == nums[i+1] && nums[i+1] == nums[i+2]) {
//                continue;
//            }
//
//            if (i < nums.length - 1 && nums[i] == nums[i+1]) {
//                int sum = target -(nums[i] + nums[i]);
//                if (map.containsKey(sum)) {
//                    List<Pair> list = map.get(sum);
//                    for (Pair pair: list) {
//                        // System.out.println(i + " " + j + " " + pair.left + " " + pair.right);
//                        if (pair.right < i) {
//                            ret.add(Arrays.asList(nums[pair.left], nums[pair.right], nums[i], nums[i]));
//                        }
//                    }
//                }
//                continue;
//            }
//
//
//            for (int j = i + 1; j < nums.length; j++) {
//
//                if (j > i + 1 && nums[j] == nums[j-1]) {
//                    continue;
//                }
//
//                int sum = target -(nums[i] + nums[j]);
//                if (map.containsKey(sum)) {
//                    List<Pair> list = map.get(sum);
//                    for (Pair pair: list) {
//                        // System.out.println(i + " " + j + " " + pair.left + " " + pair.right);
//                        if (pair.right < i) {
//                            ret.add(Arrays.asList(nums[pair.left], nums[pair.right], nums[i], nums[j]));
//                        }
//                    }
//                }
//
//
//            }
//
//        }
//        return ret;
//
//
//
//
//
//    }
//
//
//    class Pair {
//        int left;
//        int right;
//        Pair(int left, int right) {
//            this.left = left;
//            this.right = right;
//        }
//    }
//
//}
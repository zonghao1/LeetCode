/*
Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

You can assume that you can always reach the last index.



Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2


Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 105
 */

class Solution45 {
    public int jump(int[] nums) {
        int[] ret = new int[nums.length];
        for (int i = ret.length - 2; i >= 0; i--) {
            int max = nums[i] + i;
            if (max >= nums.length - 1) {
                ret[i] = 1;
                continue;
            }
            int min = nums.length;
            for (int j = max; j > i; j--) {
                min = Math.min(min, 1 + ret[j]);
            }
            ret[i] = min;
        }
        return ret[0];
    }
}
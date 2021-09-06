/*
Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.



Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 3 * 104
0 <= nums[i] <= 105
 */

class Solution55a {
    // 左到右，greedy
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (max < i) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return max >= nums.length - 1;
    }
}

class Solution55b {
    //DP 右到左
    public boolean canJump(int[] nums) {
        boolean[] ret = new boolean[nums.length];
        ret[ret.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            int max = nums[i] + i;
            for (int j = max; j >= 1; j--) {
                if (j >= nums.length - 1 || ret[j]) {
                    ret[i] = true;
                    break;
                }
            }
        }
        return ret[0];
    }
}
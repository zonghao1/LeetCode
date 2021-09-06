/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.



Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Example 2:

Input: nums = [1]
Output: 1
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23


Constraints:

1 <= nums.length <= 3 * 104
-105 <= nums[i] <= 105


Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */


//DP
class Solution53a {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prev = Math.max(prev + nums[i], nums[i]);
            max = Math.max(max, prev);
        }
        return max;
    }
}


// Divide and Conquer
class Solution53b {
    public int maxSubArray(int[] nums) {
        int[] help = helper(nums, 0, nums.length - 1);
        return help[2];
    }



    // fromLeftMax, fromRightMax, singleMax, totalSum
    int[] helper(int[] nums, int left, int right) {
        if (left == right) {
            return new int[]{nums[left], nums[left], nums[left], nums[left]};
        }

        int mid = left + (right - left) / 2;
        int[] leftPart = helper(nums, left, mid);
        int[] rightPart = helper(nums, mid + 1, right);

        int fromLeftMax = Math.max(leftPart[0], leftPart[3] + rightPart[0]);
        int fromRightMax = Math.max(rightPart[1], rightPart[3] + leftPart[1]);
        int totalSum = leftPart[3] + rightPart[3];
        int singleMax = Math.max(leftPart[2], rightPart[2]);
        singleMax = Math.max(singleMax, leftPart[1] + rightPart[0]);
        return new int[]{fromLeftMax, fromRightMax, singleMax, totalSum};



    }

}
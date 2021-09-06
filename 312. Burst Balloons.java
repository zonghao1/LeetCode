/*
You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.



Example 1:

Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
Example 2:

Input: nums = [1,5]
Output: 10


Constraints:

n == nums.length
1 <= n <= 500
0 <= nums[i] <= 100
 */

class Solution312 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] ret = new int[n+2][n+2];
        int[] newNums = new int[n+2];
        for (int i = 1; i < ret.length - 1; i++) {
            newNums[i] = nums[i-1];
        }
        nums = newNums;
        nums[0] = nums[nums.length - 1] = 1;

        for (int diff = 2; diff < nums.length; diff++) {
            for (int left = 0; left + diff < ret.length; left++) {
                int right = left + diff;
                for (int mid = left + 1; mid < right; mid++) {
                    ret[left][right] = Math.max(ret[left][right], nums[left] * nums[right] * nums[mid] + ret[left][mid] + ret[mid][right]);
                }
            }


        }
        return ret[0][ret.length - 1];


    }
}
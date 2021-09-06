/*

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.



Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]
Example 4:

Input: nums = [1]
Output: [1]


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
 */

import java.util.Arrays;

class Solution31 {
    public void nextPermutation(int[] nums) {
        int lastDigit = nums[nums.length - 1];
        int[] index = new int[]{-1};
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                index[0] = i;
                break;
            }
        }
        if (index[0] == -1) {
            Arrays.sort(nums);
            return;
        } else {
            int changeValue = nums[index[0]];
            int indexToMoveFront = index[0] + 1;
            for (int i = index[0] + 1; i < nums.length; i++) {
                if (nums[i] > changeValue) {
                    indexToMoveFront = i;
                } else break;
            }
            nums[index[0]] = nums[indexToMoveFront];
            nums[indexToMoveFront] = changeValue;
            Arrays.sort(nums, index[0] + 1, nums.length);

        }
    }
}
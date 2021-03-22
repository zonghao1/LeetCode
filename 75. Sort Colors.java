//Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
//
//        We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
//
//
//
//        Example 1:
//
//        Input: nums = [2,0,2,1,1,0]
//        Output: [0,0,1,1,2,2]
//        Example 2:
//
//        Input: nums = [2,0,1]
//        Output: [0,1,2]

class Solution75 {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int zero = 0;
        int one = 0;
        int two = nums.length - 1;
        while (one <= two) {
            if (nums[one] == 1) {
                one++;
            } else if (nums[one] == 0) {
                swap(nums, zero++, one++);
            } else {
                swap(nums, one, two--);
            }
        }




    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
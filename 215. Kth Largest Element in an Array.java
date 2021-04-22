/*
Given an integer array nums and an integer k, return the kth largest element in the array.
        Note that it is the kth largest element in the sorted order, not the kth distinct element.



        Example 1:

        Input: nums = [3,2,1,5,6,4], k = 2
        Output: 5
        Example 2:

        Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
        Output: 4
 */

class Solution215 {
    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int number;
        while ((number = helper(nums, left, right))!= nums.length - k) {
            if (number < nums.length - k) {
                left = number + 1;
            } else {
                right = number - 1;
            }
            //  number = helper(nums, left, right);
        }
        return nums[number];

    }

    int helper(int[] nums, int left, int right) {
        int pivot = nums[right];
        int r = right - 1;
        int l = left;
        while (l <= r) {
            if (nums[l] <= pivot) {
                l++;
            } else if (nums[r] > pivot) {
                r--;
            } else {
                swap(nums,l++,r--);
            }
        }
        swap(nums, l, right);
        return l;
    }



    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
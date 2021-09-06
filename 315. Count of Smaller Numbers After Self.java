/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].



Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Example 2:

Input: nums = [-1]
Output: [0]
Example 3:

Input: nums = [-1,-1]
Output: [0,0]


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
 */

import java.util.ArrayList;
import java.util.List;

class Solution315 {
    public List<Integer> countSmaller(int[] nums) {
        int[] ret = new int[nums.length];
        int[] helper = new int[nums.length];
        int[] rank = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rank[i] = i;
        }
        mergeSort(nums, ret, rank, helper, 0, nums.length - 1);
        List<Integer> list = new ArrayList<>();
        for (int i: ret) {
            list.add(i);
        }
        return list;
    }

    void mergeSort(int[] nums, int[] ret,int[] rank, int[] helper, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, ret, rank, helper, left, mid);
        mergeSort(nums, ret, rank, helper, mid + 1, right);
        merge(nums, ret, rank, helper, left, mid, mid + 1, right);
    }

    void merge(int[] nums, int[] ret, int[] rank, int[] helper, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        for (int i = leftStart; i <= rightEnd; i++) {
            helper[i] = rank[i];
        }
        int rankIndex = rightEnd;

        while (leftEnd >= leftStart || rightEnd >= rightStart) {
            if (leftEnd < leftStart || rightEnd >= rightStart && nums[helper[rightEnd]] >= nums[helper[leftEnd]]) {
                rank[rankIndex--] = helper[rightEnd--];

            } else {
                ret[helper[leftEnd]] += rightEnd - rightStart + 1;
                rank[rankIndex--] = helper[leftEnd--];
            }
        }
        return;


    }





}
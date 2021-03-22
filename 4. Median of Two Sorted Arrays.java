//Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//
//
//        Example 1:
//
//        Input: nums1 = [1,3], nums2 = [2]
//        Output: 2.00000
//        Explanation: merged array = [1,2,3] and median is 2.
//        Example 2:
//
//        Input: nums1 = [1,2], nums2 = [3,4]
//        Output: 2.50000
//        Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//        Example 3:
//
//        Input: nums1 = [0,0], nums2 = [0,0]
//        Output: 0.00000
//        Example 4:
//
//        Input: nums1 = [], nums2 = [1]
//        Output: 1.00000
//        Example 5:
//
//        Input: nums1 = [2], nums2 = []
//        Output: 2.00000

class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int firstLength = nums1.length;
        int secondLength = nums2.length;
        if ((firstLength + secondLength) % 2 != 0) {
            return findMedianSortedArrays(nums1, 0, nums2, 0, (firstLength + secondLength) / 2 + 1);
        } else {
            double first = findMedianSortedArrays(nums1, 0, nums2, 0, (firstLength + secondLength) / 2 + 1);
            double second = findMedianSortedArrays(nums1, 0, nums2, 0, (firstLength + secondLength) / 2 );
            double whatever = 1;
            return (first + second) / 2.0;
        }
    }

    double findMedianSortedArrays(int[] nums1, int nums1Start, int[] nums2, int nums2Start, int k) {
        if(nums1Start >= nums1.length) {
            return nums2[nums2Start + k - 1];
        }
        if(nums2Start >= nums2.length) {
            return nums1[nums1Start + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[nums1Start], nums2[nums2Start]);
        }
        int nums1Check = nums1Start + k / 2 - 1;
        int nums2Check = nums2Start + k / 2 - 1;
        int nums1Number = nums1Check >= nums1.length? Integer.MAX_VALUE : nums1[nums1Check];
        int nums2Number = nums2Check >= nums2.length? Integer.MAX_VALUE : nums2[nums2Check];

        if (nums1Number < nums2Number) {
            return findMedianSortedArrays(nums1, nums1Check + 1, nums2, nums2Start, k - k / 2);
        } else {
            return findMedianSortedArrays(nums1, nums1Start, nums2, nums2Check + 1, k - k / 2);
        }

    }




}
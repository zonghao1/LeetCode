//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
//
//
//
//        Example 1:
//
//
//        Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//        Output: 6
//        Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
//        Example 2:
//
//        Input: height = [4,2,0,3,2,5]
//        Output: 9

class Solution42 {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int left = height[0];
        int right = height[height.length - 1];
        int leftIndex = 1;
        int rightIndex = height.length - 2;
        int ret = 0;
        while (leftIndex <= rightIndex) {
            if (left <= right) {
                left = Math.max(left, height[leftIndex]);
                ret += Math.max(0, Math.min(left, right) - height[leftIndex]);
                leftIndex++;
            } else {
                right = Math.max(right, height[rightIndex]);
                ret += Math.max(0, Math.min(left, right) - height[rightIndex]);
                rightIndex--;
            }
        }

        return ret;
    }
}
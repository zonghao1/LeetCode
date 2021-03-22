//Given a non-negative integer x, compute and return the square root of x.
//
//        Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
//
//
//
//        Example 1:
//
//        Input: x = 4
//        Output: 2
//        Example 2:
//
//        Input: x = 8
//        Output: 2
//        Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.

class Solution69 {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        int left = 0;
        int right = x;
        while (left < right - 1) {
            int mid  = left + (right - left) / 2;
            if (x / mid == mid) {
                return mid;
            } else if (x / mid > mid) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (right <= x / right) {
            return right;
        } return left;
    }
}
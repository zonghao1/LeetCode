//
//A conveyor belt has packages that must be shipped from one port to another within D days.
//
//        The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
//
//        Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
//
//
//
//        Example 1:
//
//        Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
//        Output: 15
//        Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
//        1st day: 1, 2, 3, 4, 5
//        2nd day: 6, 7
//        3rd day: 8
//        4th day: 9
//        5th day: 10
//
//        Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
//        Example 2:
//
//        Input: weights = [3,2,2,4,1,4], D = 3
//        Output: 6
//        Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
//        1st day: 3, 2
//        2nd day: 2, 4
//        3rd day: 1, 4

class Solution1011 {
    public int shipWithinDays(int[] weights, int D) {
        int left = 0;
        int right = Integer.MAX_VALUE;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (couldWork(weights, mid, D)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (couldWork(weights, left, D)) {
            return left;
        }
        return right;
    }


    boolean couldWork(int[] weights, int work, int D) {
        int curr = 0;
        int days = 1;
        for (int weight: weights) {
            if (weight > work) {
                return false;
            }
            if (curr + weight > work) {
                days++;
                curr = weight;
            } else {
                curr += weight;
            }
        }
        return days <= D;
    }
}
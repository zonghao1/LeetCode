//You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each
// time the sliding window moves right by one position.
//
//        Return the max sliding window.
//
//Example 1:
//
//        Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//        Output: [3,3,5,5,6,7]
//        Explanation:
//        Window position                Max
//        ---------------               -----
//        [1  3  -1] -3  5  3  6  7       3
//        1 [3  -1  -3] 5  3  6  7       3
//        1  3 [-1  -3  5] 3  6  7       5
//        1  3  -1 [-3  5  3] 6  7       5
//        1  3  -1  -3 [5  3  6] 7       6
//        1  3  -1  -3  5 [3  6  7]      7
//        Example 2:
//
//        Input: nums = [1], k = 1
//        Output: [1]
//        Example 3:
//
//        Input: nums = [1,-1], k = 1
//        Output: [1,-1]
//        Example 4:
//
//        Input: nums = [9,11], k = 2
//        Output: [11]
//        Example 5:
//
//        Input: nums = [4,-2], k = 2
//        Output: [4]


import java.util.ArrayDeque;
import java.util.Deque;


class Solution239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] ret = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            while (!deque.isEmpty() && (i - deque.peekFirst() >= k)) {
                deque.pollFirst();
            }

            if (i >= k - 1) {
                ret[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return ret;

    }
}
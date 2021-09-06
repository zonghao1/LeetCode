/*
We partition a row of numbers nums into at most k adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?

Note that our partition must use every number in nums, and that scores are not necessarily integers.

Example:
Input:
nums = [9,1,2,3,9]
k = 3
Output: 20
Explanation:
The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.


Note:

1 <= nums.length <= 100.
1 <= nums[i] <= 10000.
1 <= k <= nums.length.
Answers within 10-6 of the correct answer will be accepted as correct.
 */

class Solution813a {
    public double largestSumOfAverages(int[] nums, int k) {
        double[] presum = new double[nums.length];

        double[] avg    = new double[nums.length];
        double[] helper = new double[nums.length];

        double prev = 0;
        for (int i = 0; i < nums.length; i++) {
            presum[i] = i > 0 ? presum[i-1] + nums[i] : nums[i];
            avg[i] = presum[i] / (i + 1);
        }


        for (int i = 2; i <= k; i++) {
            for (int end = i - 1; end < nums.length; end++) {
                for (int start = i - 2; start < end; start++) {
                    helper[end] = Math.max(helper[end], avg[start] + (presum[end] - presum[start]) / (end - start));
                }
            }
            avg = helper;
            helper = new double[nums.length];


        }
        return avg[avg.length - 1];




    }
}


class Solution813b {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] avg = new double[k+1][n+1];
        double[] presum = new double[n+1];
        for (int i = 1; i <= n; i++) {
            presum[i] = nums[i-1] + presum[i-1];
            avg[1][i] = presum[i] / i;
        }
        return helper(avg, presum, k, n);


    }

    double helper(double[][] avg, double[] presum, int k, int n) {
        if (avg[k][n] != 0) return avg[k][n];
        if (k == 1) return avg[k][n];
        for (int i = k - 1; i < n; i++) {
            avg[k][n] = Math.max(avg[k][n], helper(avg, presum, k -1, i) + (presum[n] - presum[i]) / (n - i));
        }
        return avg[k][n];
    }



}
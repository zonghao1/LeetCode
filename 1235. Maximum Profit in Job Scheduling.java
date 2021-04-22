/*

We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.



Example 1:



Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Example 2:



Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.
Example 3:



Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6


Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104
 */

import java.util.*;

class Solution1235 {

    // Binary Search
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        if (startTime == null || startTime.length == 0) {
            return 0;
        }
        List<Job> list = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            list.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        Collections.sort(list);

        int[] dp = new int[startTime.length];
        dp[0] = list.get(0).profit;

        for (int i = 1; i < startTime.length; i++) {
            dp[i] = dp[i-1];
            Job currJob = list.get(i);
            int index = binarySearch(list, 0, i, currJob.startTime);
            int prevProfit = 0;

            if (index != -1 && index != i) {
                prevProfit = dp[index];
            }

            dp[i] = Math.max(dp[i], prevProfit + currJob.profit);

        }
        return dp[dp.length - 1];
    }

    int binarySearch(List<Job> list, int left, int right, int target) {
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).endTime <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (list.get(right).endTime <= target) {
            return right;
        } else if (list.get(left).endTime <= target){
            return left;
        } else {
            return -1;
        }
    }




    class Job implements Comparable<Job>{
        int startTime;
        int endTime;
        int profit;
        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime   = endTime;
            this.profit    = profit;
        }


        public int compareTo(Job b) {
            return this.endTime - b.endTime;
        }

    }

}


class Solution1235b {
    // TreeMap
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        if (startTime == null || startTime.length == 0) {
            return 0;
        }
        List<Job> list = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            list.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        Collections.sort(list);

        int[] dp = new int[startTime.length + 1];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0);

        for (int i = 1; i <= startTime.length; i++) {
            dp[i] = dp[i-1];
            Job currJob = list.get(i - 1);
            Map.Entry<Integer, Integer> entry = map.floorEntry(currJob.startTime);
            dp[i] = Math.max(dp[i], dp[entry.getValue()] + currJob.profit);
            map.put(currJob.endTime, i);
        }
        return dp[dp.length - 1];




    }

    class Job implements Comparable<Job>{
        int startTime;
        int endTime;
        int profit;
        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime   = endTime;
            this.profit    = profit;
        }


        public int compareTo(Job b) {
            return this.endTime - b.endTime;
        }

    }

}
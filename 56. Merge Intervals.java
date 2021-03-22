//
//Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
//
//
//
//        Example 1:
//
//        Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//        Output: [[1,6],[8,10],[15,18]]
//        Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
//        Example 2:
//
//        Input: intervals = [[1,4],[4,5]]
//        Output: [[1,5]]
//        Explanation: Intervals [1,4] and [4,5] are considered overlapping.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution56 {
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    if (a[1] == b[1]) {
                        return 0;
                    }
                    return a[1] < b[1]? -1 : 1;
                }
                return a[0] < b[0]? -1 : 1;
            }
        });
        int[] curr = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= curr[1]) {
                curr[1] = Math.max(curr[1], intervals[i][1]);
            } else {
                list.add(curr);
                curr = intervals[i];
            }
        }
        list.add(curr);
        return list.toArray(new int[list.size()][]);
    }
}
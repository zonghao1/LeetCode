//Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
//
//
//
//        Example 1:
//
//        Input: intervals = [[0,30],[5,10],[15,20]]
//        Output: 2
//        Example 2:
//
//        Input: intervals = [[7,10],[2,4]]
//        Output: 1
//
//
//        Constraints:
//
//        1 <= intervals.length <= 104
//        0 <= starti < endi <= 106


import java.util.Arrays;

class Solution253 {
    public int minMeetingRooms(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end   = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++)  {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int max = 0;
        int startIndex = 0, endIndex = 0;
        int rooms = 0;
        while (startIndex < intervals.length) {
            if(start[startIndex] < end[endIndex]) {
                startIndex++;
                rooms++;
                max = Math.max(max, rooms);
            } else {
                endIndex++;
                rooms--;
            }
        }
        return max;
    }
}
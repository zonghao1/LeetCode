//Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
//
//
//
//        Example 1:
//
//        Input: intervals = [[0,30],[5,10],[15,20]]
//        Output: false
//        Example 2:
//
//        Input: intervals = [[7,10],[2,4]]
//        Output: true
//
//
//        Constraints:
//
//        0 <= intervals.length <= 104
//        intervals[i].length == 2
//        0 <= starti < endi <= 106

import java.util.*;

class Solution252 {
    public boolean canAttendMeetings(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        for (int[] curr: intervals) {
            list.add(curr);
        }

        Collections.sort(list, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    if (a[1] == b[1]) {
                        return 0;
                    } else if (a[1] < b[1]) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return a[0] < b[0] ? -1 : 1;
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (list.get(i)[1] > list.get(i+1)[0]) {
                return false;
            }
        }
        return true;
    }
}

class Solution252a {
    public boolean canAttendMeetings(int[][] intervals) {
        int len=intervals.length;
        if(len==0){
            return true;
        }
        int[]begin=new int[len];
        int[]stop=new int[len];
        for(int i=0;i<len;i++){
            begin[i]=intervals[i][0];
            stop[i]=intervals[i][1];
        }
        Arrays.sort(begin);
        Arrays.sort(stop);
        for(int i=1;i<len;i++){
            if(begin[i]<stop[i-1]){
                return false;
            }
        }
        return true;
    }
}
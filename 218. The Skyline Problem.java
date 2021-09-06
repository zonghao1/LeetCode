/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.

The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:

lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]



Example 1:


Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
Explanation:
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
Example 2:

Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]


Constraints:

1 <= buildings.length <= 104
0 <= lefti < righti <= 231 - 1
1 <= heighti <= 231 - 1
buildings is sorted by lefti in non-decreasing order.

 */

import java.util.*;

class Solution218 {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> ret = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) return ret;
        List<int[]> array = new ArrayList<>();

        for (int[] building: buildings) {
            array.add(new int[]{building[0], -building[2]});
            array.add(new int[]{building[1], building[2]});
        }

        Collections.sort(array, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) return a[1] - b[1];
                else return a[0] - b[0];
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.offer(0);
        int max = 0;
        for (int[] i : array) {
            if (i[1] < 0) {
                if (-i[1] > max) {
                    max = -i[1];
                    ret.add(new int[]{i[0], max});
                }
                pq.offer(-i[1]);
            } else {
                pq.remove(i[1]);
                if (i[1] >= max) {
                    max = pq.peek();
                    if (max != i[1]) ret.add(new int[]{i[0], pq.peek()});
                }
            }


        }


        return ret;

    }
}

class Solution218a {
    public List<int[]> getSkyline(int[][] buildings) {

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> b - a);
        List<int[]> ret = new ArrayList<>();

        for(int i = 0; i < buildings.length; i++){
            int start = buildings[i][0];
            int end   = buildings[i][1];
            int height = buildings[i][2];
            if(map.get(start) == null) map.put(start, new ArrayList<>());
            if(map.get(end)   == null) map.put(end,   new ArrayList<>());
            map.get(start).add(height);
            map.get(end).add(-height);
        }

        int max = 0;

        for(Integer key: map.keySet()){

            List<Integer> list = map.get(key);
            for(int i: list){
                //    System.out.println(i);
                if (i < 0) queue.remove(-i);
                else queue.add(i);
            }
            int height = queue.isEmpty()? 0 : queue.peek();
            if(height != max){
                max = height;
                ret.add(new int[]{key, max});
            }


        }
        return ret;



    }
}
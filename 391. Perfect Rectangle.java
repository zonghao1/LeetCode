/*

Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an axis-aligned rectangle. The bottom-left point of the rectangle is (xi, yi) and the top-right point of it is (ai, bi).

Return true if all the rectangles together form an exact cover of a rectangular region.



Example 1:


Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
Output: true
Explanation: All 5 rectangles together form an exact cover of a rectangular region.
Example 2:


Input: rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
Output: false
Explanation: Because there is a gap between the two rectangular regions.
Example 3:


Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
Output: false
Explanation: Because there is a gap in the top center.
Example 4:


Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
Output: false
Explanation: Because two of the rectangles overlap with each other.


Constraints:

1 <= rectangles.length <= 2 * 104
rectangles[i].length == 4
-105 <= xi, yi, ai, bi <= 105
 */

import java.util.*;

class Solution391 {
    //自己定义set的comparator可以去重
    public boolean isRectangleCover(int[][] rectangles) {
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] rectangle: rectangles) {
            int leftX = rectangle[0];
            int leftY = rectangle[1];
            int rightX = rectangle[2];
            int rightY = rectangle[3];
            min = Math.min(min, leftY);
            max = Math.max(max, rightY);
            map.putIfAbsent(leftX, new ArrayList<>());
            map.putIfAbsent(rightX, new ArrayList<>());
            int height = rightY - leftY;
            map.get(leftX).add(new int[]{leftY, height});
            map.get(rightX).add(new int[]{leftY, -height});
        }

        int totalHeight = max - min;

        TreeSet<int[]> set = new TreeSet<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[0] + Math.abs(a[1]) <= b[0]) return -1;
                if (b[0] + Math.abs(b[1]) <= a[0]) return 1;
                else return 0;
            }
        });

        int currHeight = 0;
        for (Integer key: map.keySet()) {
            List<int[]> list = map.get(key);
            Collections.sort(list, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[1] - b[1];
                }
            });
            for (int[] rec: list) {
                if (rec[1] < 0) {
                    if (!set.contains(rec)) return false;
                    set.remove(rec);
                    currHeight -= -rec[1];

                } else {
                    if (!set.add(rec)) return false;
                    set.add(rec);
                    currHeight += rec[1];
                }


            }
            if (key == map.lastKey()) {
                return currHeight == 0;
            } else {
                if (currHeight != totalHeight) return false;
            }

        }
        return true;

    }
}
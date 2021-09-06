import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
You are given an array of points in the X-Y plane points where points[i] = [xi, yi].

Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there is not any such rectangle, return 0.



Example 1:


Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:


Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Constraints:

1 <= points.length <= 500
points[i].length == 2
0 <= xi, yi <= 4 * 104
All the given points are unique.
 */
class Solution939 {
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] curr: points) {
            if (!map.containsKey(curr[0])) {
                map.put(curr[0], new HashSet<>());
            }
            map.get(curr[0]).add(curr[1]);
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < i; j++) {
                int[] first = points[i];
                int[] second = points[j];
                if (first[0] == second[0] || first[1] == second[1]) {
                    continue;
                }
                int[] third = {first[0], second[1]};
                int[] forth = {second[0], first[1]};
                if (map.get(third[0]).contains(third[1]) && map.get(forth[0]).contains(forth[1])) {
                    int xDiff = Math.abs(first[0] - second[0]);
                    int yDiff = Math.abs(first[1] - second[1]);
                    ret = Math.min(ret, (xDiff) * (yDiff));
                }
            }
        }
        return ret == Integer.MAX_VALUE? 0 : ret;
    }
}
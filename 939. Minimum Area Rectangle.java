import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Given a set of points in the xy-plane,determine the minimum area of a rectangle formed from these points,with sides parallel to the x and y axes.
//
//        If there isn't any rectangle, return 0.
//
class Solution {
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
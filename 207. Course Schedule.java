//There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
//
//        For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//        Return true if you can finish all courses. Otherwise, return false.
//
//
//
//        Example 1:
//
//        Input: numCourses = 2, prerequisites = [[1,0]]
//        Output: true
//        Explanation: There are a total of 2 courses to take.
//        To take course 1 you should have finished course 0. So it is possible.
//        Example 2:
//
//        Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//        Output: false
//        Explanation: There are a total of 2 courses to take.
//        To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
//
//
//        Constraints:
//
//        1 <= numCourses <= 105
//        0 <= prerequisites.length <= 5000
//        prerequisites[i].length == 2
//        0 <= ai, bi < numCourses
//        All the pairs prerequisites[i] are unique.

import java.util.*;

/*
    ----- Topo sort ------
 */
class Solution207 {
    final int UNVISITED = 0, VISITING = 1, VISITED = 2;

    public boolean canFinish(int numCourses, int[][] prerequisites) {


        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] pair: prerequisites) {
            int curr = pair[0];
            int pre = pair[1];
            list.get(curr).add(pre);
        }
        int[] status = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(list, status, i)) {
                return false;
            }
        }
        return true;

    }


    boolean dfs(List<List<Integer>> list, int[] status, int curr) {
        if (status[curr] == VISITED) {
            return true;
        }
        if (status[curr] == VISITING) {
            return false;
        }
        status[curr] = VISITING;
        List<Integer> requires = list.get(curr);
        for (int prev: requires) {
            if (!dfs(list, status, prev)) {
                return false;
            }
        }
        status[curr] = VISITED;
        return true;
    }


}


class Solution207a {

    //BFS

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            map.putIfAbsent(prerequisites[i][0], new HashSet<>());
            map.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            for (Map.Entry<Integer, Set<Integer>> entry: map.entrySet()) {
                if (entry.getValue().contains(curr)) {
                    entry.getValue().remove(curr);
                    if (entry.getValue().size() == 0) {
                        queue.offer(entry.getKey());
                    }
                }
            }
        }

        for (Map.Entry<Integer, Set<Integer>> entry: map.entrySet()) {
            if (entry.getValue().size() != 0) {
                return false;
            }
        }

        return true;


    }
}
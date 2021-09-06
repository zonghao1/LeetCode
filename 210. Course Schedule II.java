//
//There are a total of n courses you have to take labelled from 0 to n - 1.
//
//        Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
//
//        Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//        If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
//
//
//
//        Example 1:
//
//        Input: numCourses = 2, prerequisites = [[1,0]]
//        Output: [0,1]
//        Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
//        Example 2:
//
//        Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//        Output: [0,2,1,3]
//        Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//        So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
//        Example 3:
//
//        Input: numCourses = 1, prerequisites = []
//        Output: [0]
//
//
//        Constraints:
//
//        1 <= numCourses <= 2000
//        0 <= prerequisites.length <= numCourses * (numCourses - 1)
//        prerequisites[i].length == 2
//        0 <= ai, bi < numCourses
//        ai != bi
//        All the pairs [ai, bi] are distinct.

import java.util.ArrayList;
import java.util.List;

class Solution210 {
    final int VISITING = 1, VISITED = 2, UNVISITED = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> requires = new ArrayList<>();
        int[] status = new int[numCourses];
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            requires.add(new ArrayList<>());
        }
        for (int[] curr: prerequisites) {
            requires.get(curr[0]).add(curr[1]);
        }


        for (int i = 0; i < numCourses; i++) {
            if (!dfs(requires, status, ret, i)) {
                return new int[0];
            }
        }

        return ret.stream().mapToInt(i -> i).toArray();


    }

    boolean dfs(List<List<Integer>> requires, int[] status, List<Integer> ret, int i) {
        if (status[i] == VISITED) {
            return true;
        }
        if (status[i] == VISITING) {
            return false;
        }
        status[i] = VISITING;
        for (Integer j: requires.get(i)) {
            if (!dfs(requires, status, ret, j)) {
                return false;
            }
        }
        status[i] = VISITED;
        ret.add(i);
        return true;
    }

}
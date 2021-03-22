//There are a total of n courses you have to take labelled from 0 to n - 1.
//
//        Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
//
//        Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//        If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
//

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


//class Solution {
//    final int VISITING = 1, VISITED = 2, UNVISITED = 0;
//    public int[] findOrder(int numCourses, int[][] prerequisites) {
//        List<List<Integer>> requires = new ArrayList<>();
//        int[] status = new int[numCourses];
//        List<Integer> ret = new ArrayList<>();
//        for (int i = 0; i < numCourses; i++) {
//            requires.add(new ArrayList<>());
//        }
//        for (int[] curr: prerequisites) {
//            requires.get(curr[1]).add(curr[0]);
//        }
//        for (int i = 0; i < numCourses; i++) {
//            if (!dfs(requires, status, ret, i)) {
//                return new int[0];
//            }
//        }
//
//        int[] returns = new int[numCourses];
//        for (int i = 0; i < numCourses; i++) {
//            returns[i] = ret.get(numCourses - i - 1);
//        }
//
//        return returns;
//
//    }
//
//    boolean dfs(List<List<Integer>> requires, int[] status, List<Integer> ret, int i) {
//        if (status[i] == VISITED) {
//            return true;
//        }
//        if (status[i] == VISITING) {
//            return false;
//        }
//        status[i] = VISITING;
//        for (Integer j: requires.get(i)) {
//            if (!dfs(requires, status, ret, j)) {
//                return false;
//            }
//        }
//        status[i] = VISITED;
//        ret.add(i);
//        return true;
//    }
//
//}
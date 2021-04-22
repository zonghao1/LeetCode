/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.



Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(ret, candidates, new int[candidates.length], target, 0);
        return ret;
    }

    void dfs(List<List<Integer>> ret,int[] candidates, int[] used, int left, int level) {
        if (level == used.length) {
            if (left == 0) {
                List<Integer> currList = new ArrayList<>();
                for (int i = 0; i < used.length; i++) {
                    int use = used[i];
                    for (int j = 1; j <= use; j++) {
                        currList.add(candidates[i]);
                    }
                }
                ret.add(currList);

            }
            return;
        }

        int i = level;
        if (left - candidates[i] >= 0) {
            used[i] = 1;
            dfs(ret, candidates, used, left - candidates[i], level + 1);
            used[i] = 0;
        }
        while (i + 1 < candidates.length && candidates[i] == candidates[i+1]) {
            i++;
        }


        dfs(ret, candidates, used, left, i + 1);




    }

}
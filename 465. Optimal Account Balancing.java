/*
You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.

Return the minimum number of transactions required to settle the debt.



Example 1:

Input: transactions = [[0,1,10],[2,0,5]]
Output: 2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
Output: 1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Therefore, person #1 only need to give person #0 $4, and all debt is settled.


Constraints:

1 <= transactions.length <= 8
transactions[i].length == 3
0 <= fromi, toi <= 20
fromi != toi
1 <= amounti <= 100
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution465 {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        if (transactions == null || transactions.length == 0 || transactions[0].length == 0) {
            return 0;
        }
        for (int[] curr: transactions) {
            int fromPerson = curr[0];
            int toPerson = curr[1];
            int amount = curr[2];
            map.put(fromPerson, map.getOrDefault(fromPerson, 0) - amount);
            map.put(toPerson, map.getOrDefault(toPerson, 0) + amount);
        }
        List<Integer> list = new ArrayList<>();
        for (Integer curr: map.values()) {
            if (curr != 0) {
                list.add(curr);
            }
        }
        return dfs(list, 0);
    }

    int dfs(List<Integer> list, int index) {
        if (index == list.size()) {
            return 0;
        }
        if (list.get(index) == 0) {
            return dfs(list, index+1);
        }
        int currValue = list.get(index);
        int ret = Integer.MAX_VALUE;
        for (int i = index + 1; i < list.size(); i++) {
            if (list.get(i) * currValue > 0) {
                continue;
            }
            list.set(i, currValue + list.get(i));
            ret = Math.min(ret, 1 + dfs(list, index + 1));

            list.set(i,  list.get(i) - currValue);
            if (currValue + list.get(i)== 0) {
                break;
            }
        }
        return ret;



    }


}
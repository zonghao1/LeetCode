/*

Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.



Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You don't need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
Example 4:

Input: arr = [6,1,9]
Output: 2
Example 5:

Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
Output: 3


Constraints:

1 <= arr.length <= 5 * 104
-108 <= arr[i] <= 108
 */

import java.util.*;

class Solution1345 {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<>());
            }
            map.get(arr[i]).add(i);
        }
        int step = 0;
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                Integer curr = queue.poll();
                visited[curr] = true;
                if (curr == arr.length - 1) {
                    return step;
                }
                List<Integer> otherIndex = map.containsKey(arr[curr])? map.get(arr[curr]) : new ArrayList<>();
                for (Integer other: otherIndex) {
                    if (other != curr && !visited[other]) {
                        queue.offer(other);
                    }
                }
                map.remove(arr[curr]);
                if (curr - 1 >= 0 && !visited[curr-1]) {
                    queue.offer(curr - 1);
                }
                if (curr + 1 < arr.length && !visited[curr + 1]) {
                    queue.offer(curr + 1);
                }
            }
            step++;
        }



        return -1;
    }
}
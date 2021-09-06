/*
You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.

One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.

Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).

Note: You cannot rotate an envelope.



Example 1:

Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
Example 2:

Input: envelopes = [[1,1],[1,1],[1,1]]
Output: 1


Constraints:

1 <= envelopes.length <= 5000
envelopes[i].length == 2
1 <= wi, hi <= 104
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution354 {
    public int maxEnvelopes(int[][] e) {
        int[] ret = new int[e.length];
        Arrays.sort(e, (a, b) -> a[0] - b[0]);
        ret[0] = 1;
        for (int i = 1; i < e.length; i++) {
            ret[i] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (e[i][0] > e[j][0] && e[i][1] > e[j][1]) {
                    ret[i] = Math.max(ret[i], ret[j] + 1);
                }

            }


        }
        int max = 0;
        for (int i: ret) {
            max = Math.max(max, i);
        }
        return max;

    }
}

class Solution354a {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            }
        });

        List<Integer> list = new ArrayList<>();

        for (int[] envelope: envelopes) {
            if (list.isEmpty() || envelope[1] > list.get(list.size() - 1)) {
                list.add(envelope[1]);
            } else {
                int mid = largestSmallerOrEqual(list, envelope);
                list.set(mid+1, envelope[1]);
            }


        }
        return list.size();

    }

    int largestSmallerOrEqual(List<Integer> list, int[] curr) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) >= curr[1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (list.get(right) < curr[1]) return right;
        if (list.get(left) < curr[1]) return left;
        else return -1;



    }


}
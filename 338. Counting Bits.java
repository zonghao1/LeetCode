/*

Given an integer num, return an array of the number of 1's in the binary representation of every number in the range [0, num].



Example 1:

Input: num = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
Example 2:

Input: num = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

 */

class Solution338 {
    public int[] countBits(int num) {
        int[] ret = new int[num + 1];
        for (int i = 1; i < ret.length; i++) {
            ret[i] = ret[i >> 1] + (i & 1);
        }
        return ret;
    }
}
/*
Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.

The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.

A valid square has four equal sides with positive length and four equal angles (90-degree angles).



Example 1:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: true
Example 2:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
Output: false
Example 3:

Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
Output: true


Constraints:

p1.length == p2.length == p3.length == p4.length == 2
-104 <= xi, yi <= 104

 */

import java.util.ArrayList;
import java.util.List;

class Solution593 {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        List<Integer> length = new ArrayList<>();
        List<int[]> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                length.add(getLength(list.get(i), list.get(j)));
            }

        }
        int max = 0;
        int secondMax = 0;
        int maxCount = 0;
        int secondMaxCount = 0;
        for (int i = 0; i < length.size(); i++) {
            if (length.get(i) > max) {
                max = length.get(i);
                maxCount = 1;
            } else if (length.get(i) == max) {
                maxCount++;
            } else {
                secondMax = length.get(i);
            }
        }
        for (int i = 0; i < length.size(); i++) {
            if (length.get(i) != max && length.get(i) != secondMax) {
                return false;
            }
        }
        return maxCount == 2;

    }

    int getLength(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }


}
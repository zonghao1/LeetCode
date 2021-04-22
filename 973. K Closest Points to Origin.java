//
//Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
//
//        The distance between two points on the X-Y plane is the Euclidean distance (i.e, √(x1 - x2)2 + (y1 - y2)2).
//
//        You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
//
//
//
//        Example 1:
//
//
//        Input: points = [[1,3],[-2,2]], k = 1
//        Output: [[-2,2]]
//        Explanation:
//        The distance between (1, 3) and the origin is sqrt(10).
//        The distance between (-2, 2) and the origin is sqrt(8).
//        Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
//        We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution973 {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return squareLength(b) - squareLength(a);
            }
        });
        for (int i = 0; i < points.length; i++) {
            if (i < k) {
                pq.offer(points[i]);
            } else {
                int[] top = pq.peek();
                if (squareLength(top) > squareLength(points[i])) {
                    pq.poll();
                    pq.offer(points[i]);
                }
            }
        }
        return pq.toArray(new int[k][]);

    }

    int squareLength(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}

class Solution973a {
    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length <= 1) {
            return points;
        }

        quickSelect(points, 0, points.length - 1, k - 1);
        int[][] ret = new int[k][2];
        for (int i = 0; i < k; i++) {
            ret[i] = points[i];
        }
        return ret;

    }

    void quickSelect(int[][] points, int left, int right, int k) {
        int povitNumber = povit(points, left, right);
        if (povitNumber == k) {
            return;
        } else if (povitNumber < k) {
            quickSelect(points, povitNumber, right, k);
        } else {
            quickSelect(points, left, povitNumber - 1, k);
        }
    }



    int povit(int[][] points, int left, int right) {
        int pivotLength = squareLength(points[right]);
        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (squareLength(points[i]) <= pivotLength) {
                i++;
            } else if (squareLength(points[j]) > pivotLength) {
                j--;
            } else {
                swap(points, i++,j--);
            }
        }
        swap(points, i, right);
        return i;

    }






    void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }



    int squareLength(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
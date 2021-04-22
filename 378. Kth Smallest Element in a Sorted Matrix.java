//
//Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.
//
//        Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//
//
//
//        Example 1:
//
//        Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
//        Output: 13
//        Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
//        Example 2:
//
//        Input: matrix = [[-5]], k = 1
//        Output: -5

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution378 {

    //BFS
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (matrix[a[0]][a[1]] == matrix[b[0]][b[1]]) {
                    return 0;
                }
                return matrix[a[0]][a[1]] < matrix[b[0]][b[1]] ? -1 : 1;
            }
        });
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        pq.offer(new int[]{0,0});
        visited[0][0] = true;
        for (int i = 0; i < k - 1; i++) {
            int[] curr = pq.poll();
            int row = curr[0];
            int col = curr[1];
            if (row + 1 < matrix.length && !visited[row+1][col]) {
                visited[row+1][col] = true;
                pq.offer(new int[]{row+1,col});
            }
            if (col + 1 < matrix[0].length && !visited[row][col+1]) {
                visited[row][col+1] = true;
                pq.offer(new int[]{row, col + 1});
            }
        }
        int[] curr = pq.poll();
        return matrix[curr[0]][curr[1]];


    }
}

class Solution378a {
    public int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0];
        int right = matrix[matrix.length-1][matrix[0].length-1];
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int smaller = 0;
            smaller = wrapUpperBound(matrix,  mid);

            if (smaller < k ) {
                left = mid;
            }
            /*
            小于或等于k，找第一个满足的值
            比方说 1 2 4 5 mid = 3, 3 满足但是需要继续找，直到array里正好有一个值，这个值也一定会满足
             */

            if (smaller == k ) {
                right = mid;
            }

            if (smaller > k ) {
                right = mid;
            }

        }

        if (wrapUpperBound(matrix, left) >= k ) {
            return left;
        }
        return right;



    }
    int wrapUpperBound(int[][] matrix, int mid) {
        int smaller = 0;
        for (int[] currRow: matrix) {
            smaller += upperBound( currRow, mid);
        }
        return smaller;
    }


    // First i such that A[i] > value
    int upperBound(int[] currRow, int value) {
        int left = 0;
        int right = currRow.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (currRow[mid] <= value) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (currRow[left] > value) {
            return left;
        } else if (currRow[right] > value) {
            return right;}
        return right + 1;

    }

}
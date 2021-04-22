/*
We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.
If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.
This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.



Example 1:

Input: ["@.a.#","###.#","b.A.B"]
Output: 8
Example 2:

Input: ["@..aA","..B#.","....b"]
Output: 6


Note:

1 <= grid.length <= 30
1 <= grid[0].length <= 30
grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.
 */

import java.util.LinkedList;
import java.util.Queue;

class Solution864 {
    public final int[][] DIRS = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};
    public int shortestPathAllKeys(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();
        int keys = 0;
        int startRow = -1;
        int startCol = -1;
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = grid[i].charAt(j);
                matrix[i][j] =c;
                if (c == '@') {
                    startRow = i;
                    startCol = j;
                } else if (c >= 'a' && c <= 'f') {
                    keys++;
                }
            }
        }
        boolean[][][] visited = new boolean[rows][cols][1 << keys];
        int target = (1 << keys) - 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol, 0});
        visited[startRow][startCol][0] = true;
        int steps = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                int row = curr[0];
                int col = curr[1];
                int key = curr[2];
                char c = matrix[row][col];

                for (int[] move: DIRS) {
                    int newRow = row + move[0];
                    int newCol = col + move[1];
                    int newKey = valid(rows, cols, newRow, newCol, key, visited, matrix);
                    if (newKey == target) return steps;
                    if (newKey != -1) {
                        queue.offer(new int[]{newRow, newCol, newKey});
                    }
                }


            }
            steps++;
        }
        return -1;

    }

    int valid(int rows, int cols, int row, int col, int key, boolean[][][] visited, char[][] matrix) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return -1;
        }
        char curr = matrix[row][col];
        if (curr == '#') {
            return -1;
        }
        if (curr >= 'a' && curr <= 'f') {
            key |= 1 << (curr - 'a');
        }
        if (curr >= 'A' && curr <= 'F') {
            int requiredKey = curr - 'A';
            if ( (key & (1 << requiredKey)) == 0) {
                return -1;
            }
        }
        if (visited[row][col][key]) {
            return -1;
        }
        visited[row][col][key] = true;
        return key;

    }


}
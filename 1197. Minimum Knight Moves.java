/*

In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.



Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]


Constraints:

|x| + |y| <= 300
 */

import java.util.*;

class Solution1197 {

    class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
        @Override
        public int hashCode() {
            int result = (int) 0;
            result = 301 * result + x;
            result = 301 * result + y;
            return result;
        }

    }



    final int[][] DIRS = {{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}};

    public int minKnightMoves(int x, int y) {
        Map<Point, Integer> map = new HashMap<>();
        map.put(new Point(0,0), 0);
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0,0));
        for (int step = 1; step <= 3; step++) {
            Queue<Point> newQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                Point curr = queue.poll();
                for (int[] dir: DIRS) {
                    int newFirst = curr.x + dir[0];
                    int newSecond = curr.y + dir[1];
                    Point newPoint = new Point(newFirst, newSecond);

                    if (!map.containsKey(newPoint)) {
                        map.put(newPoint, step);
                        newQueue.offer(newPoint);
                    }
                }
            }
            queue = newQueue;
        }

        x = Math.abs(x);
        y = Math.abs(y);
        Point point = new Point(x, y);
        int steps = 0;
        while (!map.containsKey(point)) {
            int diffOne = Math.abs(x);
            int diffTwo = Math.abs(y);
            if (diffOne < diffTwo) {
                if (y > 0) {
                    y -=2;
                } else {
                    y += 2;
                }
                if (x > 0) {
                    x -=1;
                } else {
                    x += 1;
                }
            } else {
                if (y > 0) {
                    y -=1;
                } else {
                    y += 1;
                }
                if (x > 0) {
                    x -=2;
                } else {
                    x += 2;
                }
            }
            point = new Point(x, y);
            steps++;

        }
        return map.get(point) + steps;


    }
}


class Solution1197a {
    private final int[][] DIRECTIONS = new int[][] {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};

    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});

        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.remove();
                int curX = cur[0];
                int curY = cur[1];
                if (curX == x && curY == y) {
                    return result;
                }

                for (int[] d : DIRECTIONS) {
                    int newX = curX + d[0];
                    int newY = curY + d[1];
                    if (!visited.contains(newX + "," + newY) && newX >= -1 && newY >= -1) {
                        queue.add(new int[] {newX, newY});
                        visited.add(newX + "," + newY);
                    }
                }
            }
            result++;
        }
        return -1;
    }
}
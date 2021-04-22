public class Solution999 {

    public int solution(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < cols; i++) {
            dp[0][i] = matrix[0][i];
        }

        for (int i = 1; i < rows; i++) {
            int[] left = new int[cols];
            int[] right = new int[cols];
            left[0] = dp[i - 1][0];
            for (int j = 1; j < cols; j++) {
                left[j] = Math.max(left[j - 1] - 1, dp[i - 1][j]);
            }
            right[cols - 1] = dp[i - 1][cols - 1];
            for (int j = cols - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] - 1, dp[i - 1][j]);
            }

            for (int j = 0; j < cols; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
                dp[i][j] += matrix[i][j];
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < cols; i++) {
            res = Math.max(res, dp[rows - 1][i]);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution999 solution = new Solution999();
        int[][] input = new int[][]{{4, 2, 3, 7}, {5, 7, 4, 4}};
        System.out.println(solution.solution(input));
    }
}
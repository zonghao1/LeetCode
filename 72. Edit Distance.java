/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character


Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')


Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
 */

class Solution72 {
    public int minDistance(String word1, String word2) {
        int rows = word1.length() + 1;
        int cols = word2.length() + 1;
        int[][] ret = new int[rows][cols];

        for (int col = 0; col < cols; col++) {
            ret[0][col] = col;
        }

        for (int row = 1; row < rows; row++) {
            ret[row][0] = row;
            for (int col = 1; col < cols; col++) {
                if (word1.charAt(row-1) == word2.charAt(col-1)) {
                    ret[row][col] = ret[row-1][col-1];
                } else {
                    ret[row][col] = Math.min(Math.min(ret[row-1][col-1], ret[row-1][col]), ret[row][col-1]) + 1;
                }
            }
        }
        return ret[rows-1][cols-1];

    }
}
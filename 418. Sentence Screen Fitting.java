/*
Given a rows x cols screen and a sentence represented as a list of strings, return the number of times the given sentence can be fitted on the screen.

The order of words in the sentence must remain unchanged, and a word cannot be split into two lines. A single space must separate two consecutive words in a line.



Example 1:

Input: sentence = ["hello","world"], rows = 2, cols = 8
Output: 1
Explanation:
hello---
world---
The character '-' signifies an empty space on the screen.
Example 2:

Input: sentence = ["a", "bcd", "e"], rows = 3, cols = 6
Output: 2
Explanation:
a-bcd-
e-a---
bcd-e-
The character '-' signifies an empty space on the screen.
Example 3:

Input: sentence = ["i","had","apple","pie"], rows = 4, cols = 5
Output: 1
Explanation:
i-had
apple
pie-i
had--
The character '-' signifies an empty space on the screen.


Constraints:

1 <= sentence.length <= 100
1 <= sentence[i].length <= 10
sentence[i] consists of lowercase English letters.
1 <= rows, cols <= 2 * 104
 */

class Solution418 {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int row = 0;
        int index = 0;
        int count = 0;
        while (row < rows) {
            int col = 0;
            if (index == 0 && row != 0 && (row + 1) * 2 <= rows) {
                int times = rows / row;
                row *= times;
                count *=times;
                continue;
            }
            while (col + sentence[index].length() <= cols) {
                col += sentence[index].length() + 1;
                if (index == sentence.length - 1) {
                    count++;
                }
                index = (index + 1) % sentence.length;
            }
            row++;
        }
        return count;


    }
}
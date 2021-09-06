/*
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.


Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]


Constraints:

1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
words[i].length <= maxWidth
 */


import java.util.ArrayList;
import java.util.List;

class Solution68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ret;
        }
        int index = 0;
        while (index < words.length) {
            int endIndex = index;
            int length = words[endIndex].length() + 1;
            while (endIndex + 1 < words.length && length + words[endIndex + 1].length() <= maxWidth) {
                endIndex++;
                length += words[endIndex].length() + 1;
            }


            // One word or last line
            if (endIndex == index || endIndex == words.length - 1) {
                StringBuilder sb = new StringBuilder();
                for (int i = index; i <= endIndex; i++) {
                    sb.append(words[i]);
                    if (sb.length() < maxWidth) {
                        sb.append(' ');
                    }
                }
                while (sb.length() < maxWidth) {
                    sb.append(' ');
                }
                ret.add(sb.toString());



            } else {
                // Other normal cases
                int count = endIndex - index + 1;
                int interval = count - 1;
                int realLength = length - count;
                int spaces = maxWidth - realLength;

                StringBuilder sb = new StringBuilder();
                for (int i = index; i <= endIndex; i++) {
                    sb.append(words[i]);
                    int firstSpace = (int) Math.ceil((double) spaces / interval);
                    for (int j = 0; j < firstSpace; j++) {
                        sb.append(' ');
                    }
                    spaces -= firstSpace;
                    interval--;
                }
                ret.add(sb.toString());
            }
            index = endIndex + 1;




        }
        return ret;

    }
}
/*
Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.



Example 1:

Input: s = "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
Example 4:

Input: s = "  Bob    Loves  Alice   "
Output: "Alice Loves Bob"
Example 5:

Input: s = "Alice does not even like bob"
Output: "bob like even not does Alice"


Constraints:

1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.
 */

class Solution151 {
    public String reverseWords(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] array = s.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (array[fast] != ' ') {
                int start = fast;
                while (fast + 1 < array.length && array[fast+1] != ' ') {
                    fast++;
                }
                copyAndReverse(array, slow, start, fast - start + 1);
                slow += fast - start + 1;
                if (slow < array.length) {
                    array[slow++] = ' ';
                }
                fast++;
            } else {
                fast++;
            }
        }

        slow--;
        if (array[slow] == ' ') {
            slow--;
        }

        reverse(array, 0, slow);
        return new String(array, 0, slow + 1);


    }

    void copyAndReverse(char[] array, int slow, int fast, int length) {
        for (int i = 0; i < length; i++) {

            array[slow+i] = array[fast++];
        }
        fast = slow + length - 1;
        while (slow < fast) {
            char temp = array[slow];
            array[slow] = array[fast];
            array[fast] = temp;
            slow++;
            fast--;
        }
    }

    void reverse(char[] array, int slow, int fast) {
        while (slow < fast) {
            char temp = array[slow];
            array[slow++] = array[fast];
            array[fast--] = temp;
        }
    }


}
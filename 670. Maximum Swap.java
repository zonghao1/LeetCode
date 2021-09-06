/*
You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.



Example 1:

Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:

Input: num = 9973
Output: 9973
Explanation: No swap.


Constraints:

0 <= num <= 108
 */

class Solution670 {
    public int maximumSwap(int num) {
        char[] number = Integer.toString(num).toCharArray();
        int[] lastDigit = new int[10];
        for (int i = 0; i < number.length; i++) {
            lastDigit[number[i] - '0'] = i;
        }

        for (int i = 0; i < number.length; i++) {
            int thisDigit = number[i] - '0';
            for (int j = 9; j > thisDigit; j--) {
                if (lastDigit[j] > i) {
                    number[i] = (char) (j + '0');
                    number[lastDigit[j]] = (char) (thisDigit + '0');
                    return Integer.parseInt(new String(number));
                }

            }

        }
        return num;

    }
}
/*

Given a string num that contains only digits and an integer target, return all possibilities to add the binary operators '+', '-', or '*' between the digits of num so that the resultant expression evaluates to the target value.



Example 1:

Input: num = "123", target = 6
Output: ["1*2*3","1+2+3"]
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2","2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0*0","0+0","0-0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []


Constraints:

1 <= num.length <= 10
num consists of only digits.
-231 <= target <= 231 - 1
 */

import java.util.ArrayList;
import java.util.List;

class Solution282 {
    //https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
    public List<String> addOperators(String num, int target) {
        List<String> ret = new ArrayList<>();
        dfs(ret, new StringBuilder(), num, num.toCharArray(), target, 0, 0, 0);
        return ret;
    }


    void dfs(List<String> ret, StringBuilder sb, String num, char[] array, int target, int pos, long prev, long value) {
        if (pos == array.length) {
            if (value == target) {
                ret.add(sb.toString());
            }
            return;
        }

        long currNumber = 0;
        for (int i = pos; i < array.length; i++) {

            //这里错了
            if (array[i] == '0' && i != pos) break;

            currNumber = Long.parseLong(num.substring(pos, i + 1));

            String addOn = currNumber + "";

            if (pos == 0) {
                sb.append(addOn);
                dfs(ret, sb, num, array, target, i + 1, currNumber, currNumber);
                sb.delete(sb.length() - addOn.length(), sb.length());
                continue;
            }


            sb.append("+");
            sb.append(addOn);
            dfs(ret, sb,num,  array, target, i + 1, currNumber, value + currNumber);
            sb.delete(sb.length() - addOn.length()-1, sb.length());

            sb.append("-");
            sb.append(addOn);
            dfs(ret, sb, num, array, target, i + 1, -currNumber, value - currNumber);
            sb.delete(sb.length() - addOn.length()-1, sb.length());

            sb.append("*");
            sb.append(addOn);
            dfs(ret, sb, num, array, target, i + 1, prev * currNumber, value - prev + prev * currNumber);
            sb.delete(sb.length() - addOn.length()-1, sb.length());

        }




    }



}
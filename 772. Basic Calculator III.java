//Implement a basic calculator to evaluate a simple expression string.
//
//        The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
//
//        You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
//
//
//
//        Example 1:
//
//        Input: s = "1+1"
//        Output: 2
//        Example 2:
//
//        Input: s = "6-4/2"
//        Output: 4
//        Example 3:
//
//        Input: s = "2*(5+5*2)/3+(6/2+8)"
//        Output: 21
//        Example 4:
//
//        Input: s = "(2+6*3+5-(3*14/7+2)*5)+3"
//        Output: -12
//        Example 5:
//
//        Input: s = "0"
//        Output: 0

import java.util.ArrayDeque;
import java.util.Deque;

class Solution772 {
    public int calculate(String s) {
        Deque<String> queue = new ArrayDeque<>();
        Deque<Character> ops   = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    queue.offer(ops.pop() + "");
                }
                ops.pop();
            } else if (Character.isDigit(c)) {
                long curr = c - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    curr = curr * 10 + s.charAt(i+1) - '0';
                    i++;
                }
                queue.offer(curr+"");


            } else if (getLevel(c) != -1){
                while (!ops.isEmpty() && getLevel(ops.peek()) >= getLevel(c)) {
                    queue.offer(ops.pop() +"");
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            queue.offer(ops.pop()+"");
        }

        Deque<Long> ret = new ArrayDeque<>();

        while (!queue.isEmpty()) {
            String curr = queue.pollFirst();
            if (Character.isDigit(curr.charAt(0))) {
                ret.push(Long.parseLong(curr));
            } else {
                long prev = ret.pop();
                long prevPrev = ret.pop();
                if (curr.equals("+")) {
                    ret.push(prevPrev + prev);
                } else if (curr.equals("-")){
                    ret.push(prevPrev - prev);
                } else if (curr.equals("*")) {
                    ret.push(prevPrev * prev);
                } else {
                    ret.push(prevPrev / prev);
                }
            }
        }
        long retu = ret.pop();
        return (int) retu;
    }

    private int getLevel(char c) {
        if (c == '+' || c == '-') return 2;
        else if (c == '*' || c == '/') return 3;
        else return -1;
    }


}

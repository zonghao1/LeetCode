//Given a string s representing an expression, implement a basic calculator to evaluate it.
//
//
//
//        Example 1:
//
//        Input: s = "1 + 1"
//        Output: 2
//        Example 2:
//
//        Input: s = " 2-1 + 2 "
//        Output: 3
//        Example 3:
//
//        Input: s = "(1+(4+5+2)-3)+(6+8)"
//        Output: 23

import java.util.ArrayDeque;
import java.util.Deque;

class Solution224 {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<String> queue = new ArrayDeque<>();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    queue.offer(stack.pop() +"");
                }
                stack.pop();
            } else if (Character.isDigit(c)) {
                long value = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    i++;
                    value = value * 10 + (s.charAt(i) - '0');
                }
                queue.offer(value + "");
            } else if (opRank(c) != -1) {
                int rank = opRank(c);
                if ( (i == 0 || s.charAt(i-1) == '(') && opRank(c) == 2) {
                    queue.offer("0");
                }
                while (!stack.isEmpty() && rank <= opRank(stack.peek())) {
                    queue.offer(stack.pop() + "");
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            queue.offer(stack.pop() +"");
        }
        long ret = 0;
        Deque<Long> numbers = new ArrayDeque<>();
        while (!queue.isEmpty()) {
            String curr = queue.pop();
            if (Character.isDigit(curr.charAt(0))) {
                numbers.push(Long.parseLong(curr));
            } else {
                long latter = numbers.pop();
                long prev = numbers.pop();
                if (curr.charAt(0) == '+') {
                    numbers.push(prev + latter);
                } else if (curr.charAt(0) == '-') {
                    numbers.push(prev - latter);
                } else if (curr.charAt(0) == '*') {
                    numbers.push(prev - latter);
                } else if (curr.charAt(0) == '/') {
                    numbers.push(prev - latter);
                }
            }
        }

        long retLong = numbers.pop();
        return (int) retLong;
    }





    int opRank(char c) {
        if (c == '/' || c =='*') {
            return 3;
        } else if (c == '+' || c =='-') {
            return 2;
        } else {
            return -1;
        }
    }


}
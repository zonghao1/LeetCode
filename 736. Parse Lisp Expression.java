/*
You are given a string expression representing a Lisp-like expression to return the integer value of.

The syntax for these expressions is given as follows.

An expression is either an integer, a let-expression, an add-expression, a mult-expression, or an assigned variable. Expressions always evaluate to a single integer.
(An integer could be positive or negative.)
A let-expression takes the form (let v1 e1 v2 e2 ... vn en expr), where let is always the string "let", then there are 1 or more pairs of alternating variables and expressions, meaning that the first variable v1 is assigned the value of the expression e1, the second variable v2 is assigned the value of the expression e2, and so on sequentially; and then the value of this let-expression is the value of the expression expr.
An add-expression takes the form (add e1 e2) where add is always the string "add", there are always two expressions e1, e2, and this expression evaluates to the addition of the evaluation of e1 and the evaluation of e2.
A mult-expression takes the form (mult e1 e2) where mult is always the string "mult", there are always two expressions e1, e2, and this expression evaluates to the multiplication of the evaluation of e1 and the evaluation of e2.
For the purposes of this question, we will use a smaller subset of variable names. A variable starts with a lowercase letter, then zero or more lowercase letters or digits. Additionally for your convenience, the names "add", "let", or "mult" are protected and will never be used as variable names.
Finally, there is the concept of scope. When an expression of a variable name is evaluated, within the context of that evaluation, the innermost scope (in terms of parentheses) is checked first for the value of that variable, and then outer scopes are checked sequentially. It is guaranteed that every expression is legal. Please see the examples for more details on scope.
Evaluation Examples:
Input: (add 1 2)
Output: 3

Input: (mult 3 (add 2 3))
Output: 15

Input: (let x 2 (mult x 5))
Output: 10

Input: (let x 2 (mult x (let x 3 y 4 (add x y))))
Output: 14
Explanation: In the expression (add x y), when checking for the value of the variable x,
we check from the innermost scope to the outermost in the context of the variable we are trying to evaluate.
Since x = 3 is found first, the value of x is 3.

Input: (let x 3 x 2 x)
Output: 2
Explanation: Assignment in let statements is processed sequentially.

Input: (let x 1 y 2 x (add x y) (add x y))
Output: 5
Explanation: The first (add x y) evaluates as 3, and is assigned to x.
The second (add x y) evaluates as 3+2 = 5.

Input: (let x 2 (add (let x 3 (let x 4 x)) x))
Output: 6
Explanation: Even though (let x 4 x) has a deeper scope, it is outside the context
of the final x in the add-expression.  That final x will equal 2.

Input: (let a1 3 b2 (add a1 1) b2)
Output 4
Explanation: Variable names can contain digits after the first character.

Note:

The given string expression is well formatted: There are no leading or trailing spaces, there is only a single space separating different components of the string, and no space between adjacent parentheses. The expression is guaranteed to be legal and evaluate to an integer.
The length of expression is at most 2000. (It is also non-empty, as that would not be a legal expression.)
The answer and all intermediate calculations of that answer are guaranteed to fit in a 32-bit integer.
 */

import java.util.*;

class Solution736 {
    public int evaluate(String expression) {

        return evaluate(expression, new int[]{0}, new HashMap<>());
    }

    int evaluate(String expression, int[] index, Map<String, Stack<Integer>> map) {
        if (index[0] + 3 <= expression.length() && expression.substring(index[0], index[0] + 4).equals("(add")) {
            index[0] += 5;
            return evaluateAdd(expression, index, map);
        } else if (index[0] + 4 <= expression.length() && expression.substring(index[0], index[0] + 5).equals("(mult")) {
            index[0] += 6;
            return evaluateMult(expression, index, map);
        } else if (index[0] + 3 <= expression.length() && expression.substring(index[0], index[0] + 4).equals("(let")) {
            index[0] += 5;
            return evaluateLet(expression, index, map);
        }
        return 0;
    }

    int evaluateAdd(String expression, int[] index, Map<String, Stack<Integer>> map) {
        String first = getToken(expression, index);
        index[0]++;
        String second = getToken(expression, index);
        index[0]++;
        return evaluateToken(first, index, map) + evaluateToken(second, index, map);
    }

    int evaluateMult(String expression, int[] index, Map<String, Stack<Integer>> map) {
        String first = getToken(expression, index);
        index[0]++;
        String second = getToken(expression, index);
        index[0]++;
        return evaluateToken(first, index, map) * evaluateToken(second, index, map);
    }

    int evaluateLet(String expression, int[] index, Map<String, Stack<Integer>> map) {
        List<String> added = new ArrayList<>();
        int ret = 0;
        while (index[0] < expression.length()) {
            String first = getToken(expression, index);
            index[0]++;
            String second = getToken(expression, index);
            index[0]++;
            if (second == null) {
                ret =  evaluateToken(first, index, map);
                break;
            } else {
                if (map.get(first) == null) {
                    map.put(first, new Stack<>());
                }
                map.get(first).push(evaluateToken(second, index, map));
                added.add(first);
            }
        }
        for (int i = added.size() - 1; i >= 0; i--) {
            String add = added.get(i);
            map.get(add).pop();
        }
        return ret;


    }


    int evaluateToken(String expression, int[] index, Map<String, Stack<Integer>> map) {
        if (expression.charAt(0) == '-' || (expression.charAt(0) >= '0' && expression.charAt(0) <= '9')) {
            return Integer.parseInt(expression);
        } else if (expression.charAt(0) != '(') {
            Stack<Integer> curr = map.get(expression);
            return curr.peek();
        } else {
            return evaluate(expression, new int[]{0}, map);
        }
    }

    String getToken(String expression, int[] index) {
        if (index[0] >= expression.length()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        if (expression.charAt(index[0]) == '-' || (expression.charAt(index[0]) >= '0' && expression.charAt(index[0]) <= '9')) {
            while (index[0] < expression.length() && (expression.charAt(index[0]) != ' ' && expression.charAt(index[0]) != ')')) {
                sb.append(expression.charAt(index[0]));
                index[0]++;
            }
        } else if (expression.charAt(index[0]) >= 'a' && expression.charAt(index[0]) <= 'z') {
            while (index[0] < expression.length() && (expression.charAt(index[0]) != ' ' && expression.charAt(index[0]) != ')')) {
                sb.append(expression.charAt(index[0]));
                index[0]++;
            }
        } else {
            int parethesis = 1;
            sb.append('(');
            index[0]++;
            while (index[0] < expression.length() && parethesis != 0) {
                if (expression.charAt(index[0]) == '(') {
                    parethesis++;
                } else if (expression.charAt(index[0]) == ')') {
                    parethesis--;
                }
                sb.append(expression.charAt(index[0]));
                index[0]++;
            }
        }
        return sb.toString();
    }




}
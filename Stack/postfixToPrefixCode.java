package Stack;

import java.util.Stack;

public class postfixToPrefixCode {
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }
    static String postfixToPrefix(String exp) {
        Stack<String> stack = new Stack<>();
        for (char c : exp.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                stack.push(Character.toString(c));
            } else if (isOperator(c)) {
                String b = stack.pop();
                String a = stack.pop();
                stack.push(c + a + b);
            }
        }
        return stack.peek();
    }
}

package Stack;

import java.util.Stack;

public class prefixToPostfixCode {
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }
    static String prefixToPostfix(String exp) {
        Stack<String> stack = new Stack<>();
        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stack.push(Character.toString(c));
            } else if (isOperator(c)) {
                String a = stack.pop();
                String b = stack.pop();
                stack.push(a + b + c);
            }
        }
        return stack.peek();
    }
}

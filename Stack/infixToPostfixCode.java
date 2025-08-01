package Stack;

import java.util.Stack;

public class infixToPostfixCode {
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }

    // Return precedence of operators
    static int precedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': case '%': return 2;
            case '^': return 3;
        }
        return -1;
    }

    // Infix to Postfix conversion
    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : exp.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());
                if (!stack.isEmpty())
                    stack.pop(); // Remove '('
            } else if (isOperator(c)) {
                while (!stack.isEmpty() &&
                        (precedence(c) < precedence(stack.peek()) ||
                         (precedence(c) == precedence(stack.peek()) && c != '^')))
                    result.append(stack.pop());
                stack.push(c);
            }
        }
        while (!stack.isEmpty())
            result.append(stack.pop());
        return result.toString();
    }
}

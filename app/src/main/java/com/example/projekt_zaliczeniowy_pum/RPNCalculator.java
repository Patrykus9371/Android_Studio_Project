package com.example.projekt_zaliczeniowy_pum;

import java.util.Stack;

public class RPNCalculator {
    public static double calculateRPN(String expression) {
        if (expression == null || expression.isEmpty()) {
            return 0;
        }

        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split(" ");

        try {
            for (String token : tokens) {
                if (isNumeric(token)) {
                    stack.push(Double.parseDouble(token));
                } else {
                    switch (token) {
                        case "+":
                            performAddition(stack);
                            break;
                        case "-":
                            performSubtraction(stack);
                            break;
                        case "X":
                            performMultiplication(stack);
                            break;
                        case "/":
                            performDivision(stack);
                            break;
                        case "^":
                            performExponentiation(stack);
                            break;
                        case "√":
                            performSquareRoot(stack);
                            break;
                        case "%":
                            performModulo(stack);
                            break;
                    }
                }
            }
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
            return Double.NaN; // Return NaN to indicate an error
        }

        return stack.pop();
    }

    private static void performAddition(Stack<Double> stack) {
        if (stack.size() < 2) return;
        double operand2 = stack.pop();
        double operand1 = stack.pop();
        stack.push(operand1 + operand2);
    }

    private static void performSubtraction(Stack<Double> stack) {
        if (stack.size() < 2) return;
        double operand2 = stack.pop();
        double operand1 = stack.pop();
        stack.push(operand1 - operand2);
    }

    private static void performMultiplication(Stack<Double> stack) {
        if (stack.size() < 2) return;
        double operand2 = stack.pop();
        double operand1 = stack.pop();
        stack.push(operand1 * operand2);
    }

    private static void performDivision(Stack<Double> stack) {
        if (stack.size() < 2) return;
        double operand2 = stack.pop();
        double operand1 = stack.pop();
        if (operand2 == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        stack.push(operand1 / operand2);
    }

    private static void performExponentiation(Stack<Double> stack) {
        if (stack.size() < 2) return;
        double operand2 = stack.pop();
        double operand1 = stack.pop();
        stack.push(Math.pow(operand1, operand2));
    }

    private static void performSquareRoot(Stack<Double> stack) {
        if (stack.size() < 1) return;
        double operand = stack.pop();
        stack.push(Math.sqrt(operand));
    }

    private static void performModulo(Stack<Double> stack) {
        if (stack.size() < 2) return;
        double operand2 = stack.pop();
        double operand1 = stack.pop();
        stack.push(operand1 % operand2);
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

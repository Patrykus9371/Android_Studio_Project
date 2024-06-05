package com.example.projekt_zaliczeniowy_pum;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.regex.*;

public class PostfixConverter {
    public static String infixToRPN(String expression) {
        Stack<String> operatorStack = new Stack<>();
        Queue<String> outputQueue = new LinkedList<>();

        // Wyrażenie regularne do dopasowania liczb, operatorów i nawiasów
        //Pattern pattern = Pattern.compile("\\d+\\.\\d+|\\d+|\\+|\\-|\\X|\\/|\\^|\\%|\\(|\\)");

        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?|\\+|\\-|\\X|\\/|\\^|\\%||\\√|\\(|\\)|(?<![\\d.])-");



        Matcher matcher = pattern.matcher(expression);
        int count=0;
        int bracket=0;
        boolean flags=false;
        boolean flags_bracket=false;
        boolean flags_bracket_open=false;
        int minus_count=0;
        while (matcher.find()) {

            String token = matcher.group();

            if (isNumeric(token)) {
                if(flags==true)
                {
                    token="-"+token;
                    flags=false;


                }
                if(flags_bracket_open==true && Double.parseDouble(token)<0)
                {
                    double number=Double.parseDouble(token)*-1;
                    token=String.valueOf(number);
                }
                else if(flags_bracket_open==true && Double.parseDouble(token)>0)
                {
                    token="-"+token;
                }
                outputQueue.add(token);

            }
            else if(count==0 && token.equals("-") || flags_bracket==true && token.equals("-") && bracket==1 ){
                flags=true;
            }
            else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && isOperator(operatorStack.peek()) && precedence(token) <= precedence(operatorStack.peek())) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.push(token);
            } else if (token.equals("(")) {
                if(token.equals("("))
                {
                    bracket=0;
                    flags_bracket=true;
                    if(flags==true && flags_bracket==true )
                    {
                        flags_bracket_open=true;
                        flags=false;
                        minus_count++;
                        if(minus_count>1)
                        {
                            flags_bracket_open=false;
                            flags=false;
                            if(minus_count%2==1)
                            {
                                //minus_count=0;
                                flags_bracket_open=true;

                            }
                        }
                    }

                }
                operatorStack.push(token);
            } else if (token.equals(")")) {
                flags_bracket_open=false;
                minus_count=0;


                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.pop(); // Usuń '(' z stosu
            }
                if(flags_bracket==true)
                {
                    bracket++;
                }
                count++;
        }

        while (!operatorStack.isEmpty()) {
            outputQueue.add(operatorStack.pop());
        }

        return String.join(" ", outputQueue);
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return "+-X/^%√".contains(token);
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "X":
            case "/":
            case "%":
                return 2;
            case "^":
            case "√":
                return 3;
            default:
                return -1;
        }
    }
}


package com.example.projekt_zaliczeniowy_pum;

import static com.example.projekt_zaliczeniowy_pum.RPNCalculator.calculateRPN;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    // Zmienna do przechowywania aktualnego wyrażenia kalkulatora
    private StringBuilder expression = new StringBuilder();

    private void updateDisplay() {
        TextView display = findViewById(R.id.display);
        display.setText(expression.toString());
    }


    private void role_number(String buttonText)
    {
        expression.append(buttonText);
        if(expression.length() >1 ) {
            if (String.valueOf(expression.charAt(expression.length() - 2)).equals(")")) {
                expression.setLength(expression.length() - 1);
            }
        }
       if(expression.toString().equals("Error"+buttonText) || expression.toString().equals("Infinity"+buttonText) || expression.toString().equals("NaN"+buttonText))
        {
            expression.setLength(0);
            expression.append(buttonText);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Przypisanie nasłuchiwania kliknięć dla wszystkich przycisków
        Button[] buttons = new Button[]{
                findViewById(R.id.button_0), findViewById(R.id.button_1),
                findViewById(R.id.button_2), findViewById(R.id.button_3),
                findViewById(R.id.button_4), findViewById(R.id.button_5),
                findViewById(R.id.button_6), findViewById(R.id.button_7),
                findViewById(R.id.button_8), findViewById(R.id.button_9),
                findViewById(R.id.button_add), findViewById(R.id.button_minus),
                findViewById(R.id.button_X), findViewById(R.id.button_div),
                findViewById(R.id.button_equal), findViewById(R.id.button_comma),
                findViewById(R.id.button_procet), findViewById(R.id.button_add_minus),
                findViewById(R.id.button_CE), findViewById(R.id.button_C),
                findViewById(R.id.button_bracket), findViewById(R.id.button_power),
                findViewById(R.id.button_sqrt)
        };

        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick((Button) view);
                }
            });
        }
    }
    private void checkAndAddClosingBracket() {
        int openingBracketsCount = 0;
        int closingBracketsCount = 0;
        boolean numberPresent = false;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                // Jeśli znajdziemy cyfrę, ustaw flagę na true
                numberPresent = true;
            } else if (c == '(') {
                openingBracketsCount++;
            } else if (c == ')') {
                closingBracketsCount++;
            }
        }

        // Dodaj nawias otwierający, jeśli nie ma żadnej liczby
        if (!numberPresent || openingBracketsCount == closingBracketsCount || String.valueOf(expression.charAt(expression.length() - 1)).equals("(") || String.valueOf(expression.charAt(expression.length() - 1)).equals("-")){
            expression.append("(");

            if(expression.length() >1)
            {
                if(Character.isDigit(expression.charAt(expression.length() - 2)) && !String.valueOf(expression.charAt(expression.length() - 2)).equals("√"))
                {
                    expression.setLength(expression.length() - 1);
                }
            }


        } else {
            // Sprawdź, czy potrzebny jest nawias zamykający
            if (openingBracketsCount > closingBracketsCount) {
                // Jeśli jest więcej nawiasów otwierających niż zamykających,
                // dodaj nawias zamykający
                expression.append(")");
                if(!Character.isDigit(expression.charAt(expression.length() - 2) ) && !String.valueOf(expression.charAt(expression.length() - 2)).equals(")"))
                {
                    expression.setLength(expression.length() - 1);
                }
            }
        }




    }

    // Obsługa kliknięć przycisków
    private void onButtonClick(Button button) {
        String buttonText = button.getText().toString();
        switch (buttonText) {
            case "0":
                role_number(buttonText);
                break;
            case "1":
                role_number(buttonText);
                break;
            case "2":
                role_number(buttonText);
                break;
            case "3":
                role_number(buttonText);
                break;
            case "4":
                role_number(buttonText);
                break;
            case "5":
                role_number(buttonText);
                break;
            case "6":
                role_number(buttonText);
                break;
            case "7":
                role_number(buttonText);
                break;
            case "8":
                role_number(buttonText);
                break;
            case "9":
                role_number(buttonText);
                break;
            case "+":
                String operators = "+-X/%";
                expression.append(buttonText);
                if(expression.length()==1 && String.valueOf(expression.charAt(expression.length() - 1)).equals(buttonText))
                {
                    expression.setLength(0);
                }
                else if(String.valueOf(expression.charAt(expression.length() - 2)).equals(buttonText) && expression.length() > 2)
                {
                   expression.setLength(expression.length() - 1);
                }
                else if(String.valueOf(expression.charAt(expression.length() - 2)).equals("-") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("X") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("/") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("(") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("%")||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("^"))
                {
                    expression.setLength(expression.length() - 1);
                }

                if(expression.toString().equals("Error"+buttonText) || expression.toString().equals("Infinity"+buttonText) || expression.toString().equals("NaN"+buttonText))
                {
                    expression.setLength(0);
                    expression.append(buttonText);
                }


                break;
            case "-":
                expression.append(buttonText);
                if(expression.length()>1) {
                    if (String.valueOf(expression.charAt(expression.length() - 2)).equals(buttonText)) {
                        expression.setLength(expression.length() - 1);
                    } else if (String.valueOf(expression.charAt(expression.length() - 2)).equals("+") ||
                            String.valueOf(expression.charAt(expression.length() - 2)).equals("X") ||
                            String.valueOf(expression.charAt(expression.length() - 2)).equals("/"))
                    //String.valueOf(expression.charAt(expression.length() - 2)).equals("("))
                    {
                        expression.setLength(expression.length() - 1);
                    }
                }

                break;
            case "X":
                expression.append(buttonText);
                if(expression.length()==1 && String.valueOf(expression.charAt(expression.length() - 1)).equals(buttonText))
                {
                    expression.setLength(0);
                }
                else if(String.valueOf(expression.charAt(expression.length() - 2)).equals(buttonText) && expression.length() > 2)
                {
                    expression.setLength(expression.length() - 1);
                }
                else if(String.valueOf(expression.charAt(expression.length() - 2)).equals("-") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("+") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("/") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("(")||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("^"))
                {
                    expression.setLength(expression.length() - 1);
                }
                break;
            case "/":
                expression.append(buttonText);
                if(expression.length()==1 && String.valueOf(expression.charAt(expression.length() - 1)).equals(buttonText))
                {
                    expression.setLength(0);
                }
                else if(String.valueOf(expression.charAt(expression.length() - 2)).equals(buttonText) && expression.length() > 2)
                {
                    expression.setLength(expression.length() - 1);
                }
                else if(String.valueOf(expression.charAt(expression.length() - 2)).equals("-") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("X") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("+") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("(")||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("^"))
                {
                    expression.setLength(expression.length() - 1);
                }
                break;
            case ".":
                // Sprawdź, czy bieżąca liczba zawiera już kropkę dziesiętną
                int lastOperatorIndex = -1;
                for (int i = expression.length() - 1; i >= 0; i--) {
                    char c = expression.charAt(i);
                    if (c == '+' || c == '-' || c == 'X' || c == '/' || c == '%' || c == '^' || c == '(' || c == ')') {
                        lastOperatorIndex = i;
                        break;
                    }
                }

                String currentNumber = expression.substring(lastOperatorIndex + 1);
                if (currentNumber.contains(".")) {
                    // Jeśli bieżąca liczba zawiera już kropkę, przerwij operację
                    break;
                }

                // Dodaj kropkę, jeśli nie ma jej w bieżącej liczbie
                expression.append(buttonText);

                // Usuwanie błędnej kropki na początku
                if (expression.length() == 1 && String.valueOf(expression.charAt(0)).equals(buttonText)) {
                    expression.setLength(0);
                } else if (expression.length() > 1 && String.valueOf(expression.charAt(expression.length() - 2)).equals(buttonText)) {
                    expression.setLength(expression.length() - 1);
                }
                break;
            case "%":
                expression.append(buttonText);
                if(expression.length()==1 && String.valueOf(expression.charAt(expression.length() - 1)).equals(buttonText))
                {
                    expression.setLength(0);
                }
                else if(String.valueOf(expression.charAt(expression.length() - 2)).equals(buttonText) && expression.length() > 2)
                {
                    expression.setLength(expression.length() - 1);
                }
                else
                {
                    String expr = expression.toString();
                    StringBuilder newExpression = new StringBuilder();
                    int start = 0;

                    for (int i = 0; i < expr.length(); i++) {
                        if (expr.charAt(i) == '%') {
                            int end = i;
                            while (end > 0 && (Character.isDigit(expr.charAt(end - 1)) || expr.charAt(end - 1) == '.')) {
                                end--;
                            }
                            String number = expr.substring(end, i);
                            try {
                                double value = Double.parseDouble(number) / 100.0;
                                newExpression.append(expr.substring(start, end)).append(value);
                                start = i + 1;
                            } catch (NumberFormatException e) {
                                // Błąd parsowania liczby, można obsłużyć wyjątek
                                expression.setLength(0);
                                expression.append("Error");
                                updateDisplay();
                                return;
                            }
                        }
                    }

                    if (start < expr.length()) {
                        newExpression.append(expr.substring(start));
                    }

                    expression.setLength(0);
                    expression.append(newExpression.toString());
                }

                break;
            case "√":
                expression.append(buttonText+"(");
                if(expression.length()==1 && String.valueOf(expression.charAt(expression.length() - 1)).equals(buttonText))
                {
                    expression.setLength(0);
                }
                else if(String.valueOf(expression.charAt(expression.length() - 2)).equals("-") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("+") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("/") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals(")"))


                {
                    expression.setLength(expression.length() - 1);
                }

                if(expression.toString().equals("Error"+buttonText+"(") || expression.toString().equals("Infinity"+buttonText+"(") || expression.toString().equals("NaN"+buttonText+"("))
                {
                    expression.setLength(0);
                    expression.append(buttonText+"(");
                }

                if(expression.length()>2) {
                    if (Character.isDigit(expression.charAt(expression.length() - 3)) && expression.length() > 1) {
                        expression.setLength(expression.length() - 2);
                    }
                }

                break;
            case "^":
                expression.append("^");
                if(expression.length()==1 && String.valueOf(expression.charAt(expression.length() - 1)).equals(buttonText))
                {
                    expression.setLength(0);
                }
                else if(String.valueOf(expression.charAt(expression.length() - 2)).equals(buttonText) && expression.length() > 2)
                {
                    expression.setLength(expression.length() - 1);
                }
                else if(String.valueOf(expression.charAt(expression.length() - 2)).equals("-") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("+") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("/") ||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("(")||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("X")||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals("%")||
                        String.valueOf(expression.charAt(expression.length() - 2)).equals(")"))

                {
                    expression.setLength(expression.length() - 1);
                }
                break;
            case "( )":
                checkAndAddClosingBracket();
                break;
            case "+/-":
                   if (expression.length() > 0) {
                        char firstChar = expression.charAt(0);
                        if (firstChar == '-') {
                            expression.deleteCharAt(0);
                        } else {
                            expression.insert(0, '-');
                        }
                    }

                break;
            case "C":
                if(expression.length() > 0)
                {
                    expression.setLength(0);
                }

                break;
            case "CE":
                if (expression.length() >1)
                {
                    if(String.valueOf(expression.charAt(expression.length() - 2)).equals("√"))
                    {
                        expression.deleteCharAt(expression.length() - 1);
                    }
                    expression.deleteCharAt(expression.length() - 1);
                }
                break;
            case "=":
                ;

                String rpnExpression = PostfixConverter.infixToRPN(expression.toString());
                System.out.println(rpnExpression);
                double result= RPNCalculator.calculateRPN(rpnExpression);

                if (Double.isNaN(result)) {
                    expression.setLength(0);
                    expression.append("Error");
                } else {
                    expression.setLength(0);
                    expression.append(result);
                }
                break;
        }


        // Aktualizacja wyświetlanego wyrażenia na ekranie kalkulatora
        TextView display = findViewById(R.id.display);
        display.setText(expression.toString());
    }
}

package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView tvDisplay;
    private StringBuilder currentInput = new StringBuilder();
    private double firstOperand = 0;
    private String operator = "";
    private boolean waitingForSecondOperand = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        // 绑定按钮点击事件
        Button[] buttons = {
            findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2),
            findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5),
            findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8),
            findViewById(R.id.btn9), findViewById(R.id.btnDot),
            findViewById(R.id.btnClear), findViewById(R.id.btnNegate),
            findViewById(R.id.btnPercent), findViewById(R.id.btnDivide),
            findViewById(R.id.btnMultiply), findViewById(R.id.btnMinus),
            findViewById(R.id.btnPlus), findViewById(R.id.btnEquals)
        };

        for (Button btn : buttons) {
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        String charPressed = ((Button) v).getText().toString();

        switch (charPressed) {
            case "C":
                clearDisplay();
                break;
            case "±":
                negateValue();
                break;
            case "%":
                percentage();
                break;
            case ".":
                addDecimal();
                break;
            case "+":
            case "-":
            case "×":
            case "÷":
                setOperator(charPressed);
                break;
            case "=":
                calculateResult();
                break;
            default:
                addDigit(charPressed);
                break;
        }
    }

    private void addDigit(String digit) {
        if (waitingForSecondOperand) {
            currentInput.setLength(0);
            waitingForSecondOperand = false;
        }
        currentInput.append(digit);
        updateDisplay();
    }

    private void updateDisplay() {
        tvDisplay.setText(currentInput.length() > 0 ? currentInput.toString() : "0");
    }

    private void clearDisplay() {
        currentInput.setLength(0);
        firstOperand = 0;
        operator = "";
        waitingForSecondOperand = false;
        updateDisplay();
    }

    private void negateValue() {
        if (currentInput.length() > 0) {
            String currentValue = currentInput.toString();
            if (currentValue.startsWith("-")) {
                currentInput.deleteCharAt(0);
            } else {
                currentInput.insert(0, "-");
            }
            updateDisplay();
        }
    }

    private void percentage() {
        if (currentInput.length() > 0) {
            double value = Double.parseDouble(currentInput.toString());
            currentInput.setLength(0);
            currentInput.append(value / 100);
            updateDisplay();
        }
    }

    private void addDecimal() {
        if (waitingForSecondOperand) {
            currentInput.setLength(0);
            waitingForSecondOperand = false;
        }
        if (!currentInput.toString().contains(".")) {
            currentInput.append(".");
            updateDisplay();
        }
    }

    private void setOperator(String op) {
        if (currentInput.length() > 0) {
            if (operator != "" && !waitingForSecondOperand) {
                calculateResult();
            }
            firstOperand = Double.parseDouble(currentInput.toString());
            operator = op;
            waitingForSecondOperand = true;
        }
    }

    private void calculateResult() {
        if (operator != "" && currentInput.length() > 0) {
            double secondOperand = Double.parseDouble(currentInput.toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "×":
                    result = firstOperand * secondOperand;
                    break;
                case "÷":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        tvDisplay.setText("错误");
                        clearDisplay();
                        return;
                    }
                    break;
            }

            currentInput.setLength(0);
            currentInput.append(String.format("%.2f", result).replaceAll("0*$", "").replaceAll("\\.$", ""));
            operator = "";
            waitingForSecondOperand = true;
            updateDisplay();
        }
    }
}

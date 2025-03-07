package CalculatorStates;

import java.util.ArrayList;
import java.util.List;

public class CalculatorContext {
    private final List<DisplayObserver> observers = new ArrayList<>();
    private String displayText = "";

    private String currentInput = "";
    private String result = "";

    private CalculatorState state = new StartState();

    public CalculatorContext() {
        this.state = new StartState();
    }

    public void addObserver(DisplayObserver observer) {
        observers.add(observer);
    }



    public void handleDigit(String digit) {
        state.handleDigit(this, digit);
        updateDisplay();
    }

    public void handleOperator(String operator) {
        state.handleOperator(this, operator);
        updateDisplay();
    }
    public void handleEquals() {
        state.handleEquals(this);
        updateDisplay();
    }

    public void handleClear() {
        state.handleClear(this);
    }


    //Evaluators
    void evaluate() {
        try {
            result = String.valueOf(eval(currentInput));
            displayText = result;
            state = new ResultState();
            updateDisplay();
        } catch (Exception e) {
            displayText = "Error";
            result = "";
            state = new ErrorState();
            updateDisplay();
        }
    }

    private double eval(String expression) {
        // Add your expression evaluation logic
        return 0;
    }


    // State transition methods
    void setState(CalculatorState state) {
        this.state = state;
    }

    void reset() {
        currentInput = "";
        result = "";
        displayText = "";
        state = new StartState();
        updateDisplay();
    }



    //Display Method
    private void updateDisplay() {
        String cleanedText = displayText.replaceAll("\\s+$", "");
        for (DisplayObserver observer : observers) {
            observer.updateDisplay(cleanedText);
        }
    }

    // Getters and setters
    public void appendInput(String str) { currentInput += str; }
    void setCurrentInput(String input) { currentInput = input; }
    public void appendDisplay(String str) { displayText += str; }
    void setDisplayText(String text) { displayText = text; }
}
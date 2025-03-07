package CalculatorStates;

import java.util.ArrayList;
import java.util.List;

import Expressions.Expression;
import Expressions.SerializationVisitor;
import Expressions.SymbolParser;

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


    void evaluate() {
        try {
            // 1. Build expression tree from input
            Expression expression = buildExpressionTree(currentInput);
            
            // 2. Serialize and evaluate using visitor
            SerializationVisitor serializer = new SerializationVisitor();
            expression.accept(serializer); // Recursively visit all nodes
            
            // 3. Get result from visitor
            result = "" + serializer.getResult();
            
            // 4. Send to server and update display
            //sendToServer(serializer.getFullMessage());
            
            displayText = String.valueOf(result);
            state = new ResultState();
        } catch (Exception e) {
            displayText = "Error";
            result = "";
            state = new ErrorState();
        }
        updateDisplay();
    }

    private Expression buildExpressionTree(String input) {
        // Implement your parser here
        // This could use the Interpreter pattern
        return new SymbolParser(input).parse();
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
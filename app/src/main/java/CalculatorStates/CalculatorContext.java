package CalculatorStates;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Expressions.Expression;
import Expressions.SerializationVisitor;
import Expressions.SymbolParser;

public class CalculatorContext {
    private final List<DisplayObserver> observers = new ArrayList<>();

    private final PrintWriter serverOut;

    private String displayText = "";

    private String currentInput = "";

    private String result = "";

    private CalculatorState state = new StartState();

    public CalculatorContext(PrintWriter serverOut) {
        this.state = new StartState();
        this.serverOut = serverOut;
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
        System.out.println("Evaluating");
        
        try {
            // 1. Build expression tree from input
            Expression expression = buildExpressionTree(currentInput);
            
            // 2. Serialize and evaluate using visitor
            SerializationVisitor serializer = new SerializationVisitor();
            expression.accept(serializer); // Recursively visit all nodes
            
            // 3. Get result from visitor
            result = "" + serializer.getResult();
            
            displayText = String.valueOf(result);

            //4. Send to Server
            serverOut.println(displayText);

            state = new ResultState();
        } catch (Exception e) {
            displayText = "Error";
            result = "";
            state = new ErrorState();
        }

        updateDisplay();
    }

    private Expression buildExpressionTree(String input) {
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

    public void discardLastInput() {
        if (!currentInput.isEmpty()) {
            displayText = currentInput;
            updateDisplay();
        }
    }

    public void promptForErrorAction() {
        notifyObserversForError(
            this::reset,          // Reset callback
            this::discardLastInput // Discard callback
        );
    }
    
    private void notifyObserversForError(Runnable resetAction, Runnable discardAction) {
        for (DisplayObserver observer : observers) {
            observer.showErrorPrompt(resetAction, discardAction);
        }
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
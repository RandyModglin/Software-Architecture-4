package CalculatorStates;

public class ErrorState implements CalculatorState {
    
    @Override
    public void handleDigit(CalculatorContext context, String digit) {
        // Block all input during error state
    }
    
    @Override
    public void handleOperator(CalculatorContext context, String operator) {
        // Block all input during error state
    }
    
    @Override
    public void handleEquals(CalculatorContext context) {
        // Block all input during error state
    }
    
    @Override
    public void handleClear(CalculatorContext context) {
        context.reset();
    }
}

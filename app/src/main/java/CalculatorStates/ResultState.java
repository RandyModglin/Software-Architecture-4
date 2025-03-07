package CalculatorStates;
//Concrete State

public class ResultState implements CalculatorState {
    @Override
    public void handleDigit(CalculatorContext context, String digit) {
        context.reset();
        context.handleDigit(digit);
    }
    
    @Override
    public void handleOperator(CalculatorContext context, String operator) {
        context.reset();
    }
    
    @Override
    public void handleEquals(CalculatorContext context) {
    }
    
    @Override
    public void handleClear(CalculatorContext context) {
        context.reset();
    }
}
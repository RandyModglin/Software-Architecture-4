package CalculatorStates;

public class OperatorState implements CalculatorState {
    
    @Override
    public void handleDigit(CalculatorContext context, String digit) {
        context.appendInput(digit);
        context.appendDisplay(digit);
        context.setState(new DigitState());
    }
    
    @Override
    public void handleOperator(CalculatorContext context, String operator) {
        //Add Error handling
    }
    
    @Override
    public void handleEquals(CalculatorContext context) {
        // Ignore equals in operator state
    }
    
    @Override
    public void handleClear(CalculatorContext context) {
        context.reset();
    }
}
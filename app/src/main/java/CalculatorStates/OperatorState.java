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
        context.promptForErrorAction();
    }
    
    @Override
    public void handleEquals(CalculatorContext context) {
        context.promptForErrorAction();
    }
    
    @Override
    public void handleClear(CalculatorContext context) {
        context.reset();
    }
}
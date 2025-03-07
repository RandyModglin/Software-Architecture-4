package CalculatorStates;
//Concrete State

public class StartState implements CalculatorState {
    
    @Override
    public void handleDigit(CalculatorContext context, String digit) {
        context.setCurrentInput(digit);
        context.setDisplayText(digit);
        context.setState(new DigitState());
    }
    
    @Override
    public void handleOperator(CalculatorContext context, String operator) {
        // Ignore operator in start state
    }
    
    @Override
    public void handleEquals(CalculatorContext context) {
        // Ignore equals in start state
    }
    
    @Override
    public void handleClear(CalculatorContext context) {
        context.reset();
    }
}

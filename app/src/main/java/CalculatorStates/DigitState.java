package CalculatorStates;
//Concrete State

class DigitState implements CalculatorState {
    
    @Override
    public void handleDigit(CalculatorContext context, String digit) {
        context.appendInput(digit);
        context.appendDisplay(digit);
    }
    
    @Override
    public void handleOperator(CalculatorContext context, String operator) {
        context.appendInput(operator);
        context.appendDisplay(" " + operator + " ");
        context.setState(new OperatorState());
    }
    
    @Override
    public void handleEquals(CalculatorContext context) {
        context.evaluate();
        context.setState(new ResultState());
    }
    
    @Override
    public void handleClear(CalculatorContext context) {
        context.reset();
    }
}
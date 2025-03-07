package CalculatorStates;
//State Interface

public interface CalculatorState {
    void handleDigit(CalculatorContext context, String digit);
    void handleOperator(CalculatorContext context, String operator);
    void handleEquals(CalculatorContext context);
    void handleClear(CalculatorContext context);
}
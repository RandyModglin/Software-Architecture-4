package CalculatorStates;

public interface DisplayObserver {
    void updateDisplay(String displayText);
    void showErrorPrompt(Runnable onReset, Runnable onDiscard); // Callbacks for actions
}
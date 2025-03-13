package GUI;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import CalculatorStates.DisplayObserver;

public class LabelComponent implements GUIComponent, DisplayObserver {
    @SuppressWarnings("FieldMayBeFinal")
    private JLabel label;

    public LabelComponent() {
        label = new JLabel();
        label.setPreferredSize(new Dimension(300, 50));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    @Override
    public void showErrorPrompt(Runnable onReset, Runnable onDiscard) {
        // Show dialog directly (no need for frame reference)
        int choice = JOptionPane.showOptionDialog(
            null,  // Use null for parent component
            "Consecutive operators detected!\nWould you like to reset or discard?",
            "Invalid Input",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.ERROR_MESSAGE,
            null,
            new String[]{"Reset", "Discard"},
            "Discard"
        );

        // Execute the appropriate callback
        if (choice == JOptionPane.YES_OPTION) {
            onReset.run();
        } else {
            onDiscard.run();
        }
    }

    public void SetText(String text) {
        label.setText(text);
    }

    public String GetText() {
        return label.getText();
    }

    @Override
    public JComponent GetComponent() {
        return label;
    }

    @Override
    public void updateDisplay(String text) {
        SetText(text);
    }
}
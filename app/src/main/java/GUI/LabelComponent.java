package GUI;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
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
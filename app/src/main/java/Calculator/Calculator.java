package Calculator;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LabelComponent output = new LabelComponent();
        CompositePanel buttonPanel = new CompositePanel(new GridLayout(4, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(e -> {
                if (label.equals("C")) {
                    output.SetText("");
                } else {
                    output.SetText(output.GetText() + label);
                }
            });
            buttonPanel.Add(new LeafComponent(button));
        }

        CompositePanel mainPanel = new CompositePanel(new BorderLayout());
        mainPanel.Add(output, BorderLayout.NORTH);
        mainPanel.Add(buttonPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel.GetComponent());
        frame.pack();
        frame.setVisible(true);
    }
}
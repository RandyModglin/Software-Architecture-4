

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import CalculatorStates.CalculatorContext;
import GUI.CompositePanel;
import GUI.LabelComponent;
import GUI.LeafComponent;

public class Calculator{
    
    private final JFrame frame = new JFrame("Calculator");

    private LabelComponent output;
    private CalculatorContext context;

    public void start() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        output = new LabelComponent();
        context = new CalculatorContext();
        
        context.addObserver(output);

        
        CompositePanel buttonPanel = new CompositePanel(new GridLayout(4, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(e -> handleButtonPress(context, label));
            buttonPanel.Add(new LeafComponent(button));
        }

        CompositePanel mainPanel = new CompositePanel(new BorderLayout());
        mainPanel.Add(output, BorderLayout.NORTH);
        mainPanel.Add(buttonPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel.GetComponent());
        frame.pack();
        frame.setVisible(true);
    }

    private static void handleButtonPress(CalculatorContext context, String label) {
        if (label.equals("C")) {
            context.handleClear();
        } else if (label.equals("=")) {
            context.handleEquals();
        } else if (isOperator(label)) {
            context.handleOperator(label);
        } else {
            context.handleDigit(label);
        }
    }

    private static boolean isOperator(String label) {
        return label.matches("[+\\-*/]");
    }
}
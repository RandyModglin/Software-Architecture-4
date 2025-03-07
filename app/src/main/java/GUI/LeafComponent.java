package GUI;
// LeafComponent class

import javax.swing.JComponent;

public class LeafComponent implements GUIComponent {
    private final JComponent component;

    public LeafComponent(JComponent component) {
        this.component = component;
    }

    @Override
    public JComponent GetComponent() {
        return component;
    }
}
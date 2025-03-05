package Calculator;
// LeafComponent class

import javax.swing.JComponent;

class LeafComponent implements GUIComponent {
    private final JComponent component;

    public LeafComponent(JComponent component) {
        this.component = component;
    }

    @Override
    public JComponent GetComponent() {
        return component;
    }
}
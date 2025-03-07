package GUI;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CompositePanel implements GUIComponent{

    private final JPanel panel;
    private java.util.List<GUIComponent> children = new java.util.ArrayList<>();

    public CompositePanel(LayoutManager layout) {
        panel = new JPanel(layout);
    }

    public void Add(GUIComponent component, Object constraints) {
        children.add(component);
        panel.add(component.GetComponent(), constraints);
    }

    public void Add(GUIComponent component) {
        children.add(component);
        panel.add(component.GetComponent());
    }

    @Override
    public JComponent GetComponent() {
        return panel;
    }
    
}

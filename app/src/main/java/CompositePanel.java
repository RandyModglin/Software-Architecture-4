import javax.swing.*;
import java.awt.*;

public class CompositePanel implements GUIComponent{

    private JPanel panel;
    private java.util.List<GUIComponent> children = new java.util.ArrayList<>();

    public CompositePanel(LayoutManager layout) {
        panel = new JPanel(layout);
    }

    public void add(GUIComponent component, Object constraints) {
        children.add(component);
        panel.add(component.getComponent(), constraints);
    }

    public void add(GUIComponent component) {
        children.add(component);
        panel.add(component.getComponent());
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }
    
}

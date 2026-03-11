package TemplateMethod;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DrawingPanel extends JPanel {
    private List<MovingShape> shapes = new CopyOnWriteArrayList<>();

    public void addShape(MovingShape shape) {
        shapes.add(shape);
    }

    public void clearShapes() {
        shapes.clear();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (MovingShape shape : shapes) {
            shape.draw(g2d);
        }
    }

    public List<MovingShape> getShapes() {
        return shapes;
    }
}

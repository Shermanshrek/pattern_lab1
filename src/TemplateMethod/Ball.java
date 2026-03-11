package TemplateMethod;

import java.awt.Graphics2D;
import java.awt.Color;

public class Ball extends MovingShape {
    public Ball(int startX, int startY, int size, int dx, int dy, int panelWidth, int panelHeight) {
        super(startX, startY, size, dx, dy, panelWidth, panelHeight);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
    }
}

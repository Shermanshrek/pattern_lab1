package TemplateMethod;

import java.awt.Graphics2D;
import java.awt.Color;

public class Square extends MovingShape {
    public Square(int startX, int startY, int size, int dx, int dy, int panelWidth, int panelHeight) {
        super(startX, startY, size, dx, dy, panelWidth, panelHeight);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, size, size);
    }
}

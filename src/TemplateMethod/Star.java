package TemplateMethod;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Polygon;

public class Star extends MovingShape {
    public Star(int startX, int startY, int size, int dx, int dy, int panelWidth, int panelHeight) {
        super(startX, startY, size, dx, dy, panelWidth, panelHeight);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
        int[] xPoints = new int[10];
        int[] yPoints = new int[10];
        int cx = x + size / 2;
        int cy = y + size / 2;
        int outerR = size / 2;
        int innerR = size / 4;
        for (int i = 0; i < 10; i++) {
            double angle = Math.PI / 2 - i * Math.PI / 5; // начинаем с верхней точки
            int r = (i % 2 == 0) ? outerR : innerR;
            xPoints[i] = cx + (int) (r * Math.cos(angle));
            yPoints[i] = cy - (int) (r * Math.sin(angle));
        }
        g.fillPolygon(new Polygon(xPoints, yPoints, 10));
    }
}

package TemplateMethod;

import java.awt.Graphics2D;

public abstract class MovingShape {
    protected int x, y;          // текущие координаты левого верхнего угла
    protected int dx, dy;        // скорость по осям
    protected int size;          // размер фигуры (ширина и высота)
    protected int panelWidth, panelHeight; // границы

    public MovingShape(int startX, int startY, int size, int dx, int dy, int panelWidth, int panelHeight) {
        this.x = startX;
        this.y = startY;
        this.size = size;
        this.dx = dx;
        this.dy = dy;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }


    public final void move() {
        // обновление позиции
        x += dx;
        y += dy;

        // проверка столкновения с границами
        if (x < 0) {
            x = 0;
            dx = -dx;
        } else if (x + size > panelWidth) {
            x = panelWidth - size;
            dx = -dx;
        }

        if (y < 0) {
            y = 0;
            dy = -dy;
        } else if (y + size > panelHeight) {
            y = panelHeight - size;
            dy = -dy;
        }
    }


    public abstract void draw(Graphics2D g);
}

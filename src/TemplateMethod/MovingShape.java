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

    /**
     * Шаблонный метод, задающий алгоритм движения фигуры.
     * Обновляет позицию и обрабатывает столкновения со стенами.
     */
    public final void move() {
        // Шаг 1: обновление позиции
        x += dx;
        y += dy;

        // Шаг 2: проверка столкновений с границами
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

    /**
     * Абстрактный метод для отрисовки фигуры.
     * Конкретные подклассы реализуют его по-своему.
     */
    public abstract void draw(Graphics2D g);
}

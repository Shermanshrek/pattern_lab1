package State;

import java.awt.Graphics2D;

public class HappyState implements State {
    @Override
    public void draw(Graphics2D g, int x, int y) {
        // Тело
        g.drawLine(x, y + 30, x, y + 80);
        g.drawLine(x, y + 40, x - 30, y + 50);
        g.drawLine(x, y + 40, x + 30, y + 50);
        g.drawLine(x, y + 80, x - 20, y + 120);
        g.drawLine(x, y + 80, x + 20, y + 120);
        // Голова
        g.drawOval(x - 30, y - 30, 60, 60);
        // Глаза открыты (круги)
        g.fillOval(x - 15, y - 15, 8, 8);
        g.fillOval(x + 7, y - 15, 8, 8);
        // Улыбка
        g.drawArc(x - 15, y, 30, 15, 0, -180);
    }
}

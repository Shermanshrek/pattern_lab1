package State;

import java.awt.Graphics2D;
import java.awt.Color;

public class SleepingState implements State {
    @Override
    public void draw(Graphics2D g, int x, int y) {
        // Тело
        g.drawLine(x, y + 30, x, y + 80);
        // Руки
        g.drawLine(x, y + 40, x - 30, y + 50);
        g.drawLine(x, y + 40, x + 30, y + 50);
        // Ноги
        g.drawLine(x, y + 80, x - 20, y + 120);
        g.drawLine(x, y + 80, x + 20, y + 120);
        // Голова
        g.drawOval(x - 30, y - 30, 60, 60);
        // Глаза закрыты (линии)
        g.drawLine(x - 15, y - 10, x - 5, y - 10);
        g.drawLine(x + 5, y - 10, x + 15, y - 10);
        // Рот — нейтральная линия
        g.drawLine(x - 10, y + 10, x + 10, y + 10);
        // Символ сна
        g.drawString("Zzz", x + 20, y - 40);
    }
}

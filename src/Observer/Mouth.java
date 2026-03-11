package Observer;

import java.awt.*;
import java.awt.geom.Arc2D;

public class Mouth extends FacePart {
    private boolean smiling = true; // true - улыбка, false - нейтральная линия

    public Mouth(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public boolean contains(Point p) {
        return new Rectangle(x, y, width, height).contains(p);
    }

    @Override
    public void handleClick() {
        smiling = !smiling;
        setChanged();
        notifyObservers();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        if (smiling) {
            // Улыбка
            g2d.draw(new Arc2D.Double(x, y, width, height, 0, -180, Arc2D.OPEN));
        } else {
            // Нейтральное
            g2d.drawLine(x, y + height / 2, x + width, y + height / 2);
        }
    }
}

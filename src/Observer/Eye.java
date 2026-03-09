package Observer;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Eye extends FacePart {
    private boolean open = true;

    public Eye(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public boolean contains(Point p) {
        return new Ellipse2D.Double(x, y, width, height).contains(p);
    }

    @Override
    public void handleClick() {
        open = !open;
        setChanged();
        notifyObservers();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x, y, width, height);
        if (open) {
            g2d.fillOval(x + width / 3, y + height / 3, width / 3, height / 3);
        } else {
            g2d.drawLine(x, y + height / 2, x + width, y + height / 2);
        }
    }
}

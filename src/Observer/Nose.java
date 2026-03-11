package Observer;

import java.awt.*;

public class Nose extends FacePart {
    private Color color = Color.RED;

    public Nose(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public boolean contains(Point p) {
        return new Rectangle(x, y, width, height).contains(p);
    }

    @Override
    public void handleClick() {
        if (color == Color.RED) color = Color.BLUE;
        else if (color == Color.BLUE) color = Color.GREEN;
        else color = Color.RED;
        setChanged();
        notifyObservers();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        int[] xPoints = {x + width / 2, x, x + width};
        int[] yPoints = {y, y + height, y + height};
        g2d.fillPolygon(xPoints, yPoints, 3);
    }
}

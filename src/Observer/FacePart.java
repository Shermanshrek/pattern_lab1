package Observer;

import java.awt.*;

public abstract class FacePart extends Observable {
    protected int x, y, width, height;

    public FacePart(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract boolean contains(Point p);

    public abstract void handleClick();

    public abstract void draw(Graphics2D g2d);
}

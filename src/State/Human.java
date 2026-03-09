package State;

import java.awt.Graphics2D;

public class Human {
    private State state;
    private int x, y;

    public Human(int x, int y) {
        this.x = x;
        this.y = y;
        this.state = new HappyState(); // начальное состояние
    }

    public void setState(State state) {
        this.state = state;
    }

    public void draw(Graphics2D g) {
        state.draw(g, x, y);
    }
}

package facade;

class Car {
    private double x;
    private final int y;
    private final int width;
    private final int height;
    private final int speed;
    private boolean stopped = false;

    public Car(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public void move() {
        if (!stopped) {
            x += speed;
        }
    }

    public void stop() { stopped = true; }
    public void go() { stopped = false; }

    public double getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
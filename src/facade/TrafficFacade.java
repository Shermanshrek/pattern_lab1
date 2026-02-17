package facade;

public class TrafficFacade {
    // Публичный enum для цвета светофора (доступен клиентам)
    public enum LightColor { RED, YELLOW, GREEN }

    private Car car;
    private TrafficLight light;
    private int lightX = 400;
    private int lightY = 100;
    private int carY = 200;

    private static final int LIGHT_CYCLE_FRAMES = 60;
    private int frameCounter = 0;

    public TrafficFacade() {
        car = new Car(50, carY, 60, 30, 2);
        light = new TrafficLight();
    }

    public void update() {
        frameCounter++;
        if (frameCounter >= LIGHT_CYCLE_FRAMES) {
            light.nextColor();
            frameCounter = 0;
        }

        TrafficLight.Color currentColor = light.getColor();
        double carX = car.getX();

        if (currentColor == TrafficLight.Color.RED && carX < lightX) {
            car.stop();
        } else {
            car.go();
        }

        car.move();
    }

    // Возвращает текущий цвет светофора в виде публичного enum'а
    public LightColor getLightColor() {
        switch (light.getColor()) {
            case RED:    return LightColor.RED;
            case YELLOW: return LightColor.YELLOW;
            case GREEN:  return LightColor.GREEN;
            default:     return LightColor.RED; // не должно достигаться
        }
    }

    // Методы для отрисовки
    public int getCarX() { return (int) car.getX(); }
    public int getCarY() { return carY; }
    public int getCarWidth() { return car.getWidth(); }
    public int getCarHeight() { return car.getHeight(); }
    public int getLightX() { return lightX; }
    public int getLightY() { return lightY; }
}
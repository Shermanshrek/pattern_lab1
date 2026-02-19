package facade;

public class TrafficFacade {
    public enum LightColor { RED, YELLOW, GREEN }

    private Car car;
    private TrafficLight light;
    private int lightX = 700;
    private int lightY = 100;
    private int carY = 200;

    private static final int LIGHT_CYCLE_FRAMES = 60;
    private int frameCounter = 0;

    public TrafficFacade() {
        car = new Car(50, carY, 60, 30, 2); // начальная x=50
        light = new TrafficLight();
    }

    public void update(int windowWidth) {
        // Переключение светофора
        frameCounter++;
        if (frameCounter >= LIGHT_CYCLE_FRAMES) {
            light.nextColor();
            frameCounter = 0;
        }

        // Логика остановки автомобиля
        TrafficLight.Color currentColor = light.getColor();
        double carX = car.getX();

        if (currentColor == TrafficLight.Color.RED && carX < lightX) {
            car.stop();
        } else {
            car.go();
        }

        car.move();

        // Проверка выхода за правую границу: если автомобиль полностью скрылся, перемещаем влево
        if (car.getX() > windowWidth) {
            car.setX(-car.getWidth());  // появляется слева за границей
        }
    }

    public LightColor getLightColor() {
        return switch (light.getColor()) {
            case YELLOW -> LightColor.YELLOW;
            case GREEN -> LightColor.GREEN;
            default -> LightColor.RED;
        };
    }

    public int getCarX() { return (int) car.getX(); }
    public int getCarY() { return carY; }
    public int getCarWidth() { return car.getWidth(); }
    public int getCarHeight() { return car.getHeight(); }
    public int getLightX() { return lightX; }
    public int getLightY() { return lightY; }
}
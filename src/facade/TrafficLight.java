package facade;

class TrafficLight {
    public enum Color { RED, YELLOW, GREEN }
    private Color currentColor = Color.RED;

    public void nextColor() {
        switch (currentColor) {
            case RED:    currentColor = Color.YELLOW; break;
            case YELLOW: currentColor = Color.GREEN; break;
            case GREEN:  currentColor = Color.RED; break;
        }
    }

    public Color getColor() { return currentColor; }
}
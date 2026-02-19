package simulation;

import facade.TrafficFacade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficSimulation extends JPanel {
    private TrafficFacade facade;
    private Timer timer;

    public TrafficSimulation() {
        facade = new TrafficFacade();
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Передаём текущую ширину панели в фасад
                facade.update(getWidth());
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Дорога
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, facade.getCarY() + 20, getWidth(), 10);

        // Светофор
        int lightX = facade.getLightX();
        int lightY = facade.getLightY();
        g.setColor(Color.BLACK);
        g.fillRect(lightX - 10, lightY - 30, 20, 80);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(lightX - 20, lightY - 40, 40, 90);

        TrafficFacade.LightColor color = facade.getLightColor();
        drawLight(g, lightX, lightY - 20, Color.RED, color == TrafficFacade.LightColor.RED);
        drawLight(g, lightX, lightY, Color.YELLOW, color == TrafficFacade.LightColor.YELLOW);
        drawLight(g, lightX, lightY + 20, Color.GREEN, color == TrafficFacade.LightColor.GREEN);

        // Автомобиль
        int carX = facade.getCarX();
        int carY = facade.getCarY();
        g.setColor(Color.BLUE);
        g.fillRect(carX, carY, facade.getCarWidth(), facade.getCarHeight());
        g.setColor(Color.BLACK);
        g.fillOval(carX + 5, carY + facade.getCarHeight() - 10, 10, 10);
        g.fillOval(carX + facade.getCarWidth() - 15, carY + facade.getCarHeight() - 10, 10, 10);
    }

    private void drawLight(Graphics g, int x, int y, Color color, boolean active) {
        g.setColor(active ? color : Color.GRAY);
        g.fillOval(x - 10, y - 10, 20, 20);
        g.setColor(Color.BLACK);
        g.drawOval(x - 10, y - 10, 20, 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Traffic Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.add(new TrafficSimulation());
        frame.setVisible(true);
    }
}
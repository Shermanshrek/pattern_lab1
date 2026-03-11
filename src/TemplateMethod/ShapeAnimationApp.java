package TemplateMethod;

import javax.swing.*;
import java.awt.*;

public class ShapeAnimationApp extends JFrame {
    private DrawingPanel drawingPanel;
    private volatile boolean running = true;
    private Thread animationThread;
    private int shapeCounter = 0; // для чередования типов фигур

    public ShapeAnimationApp() {
        setTitle("Прыгающие фигуры");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Пуск");
        JButton closeButton = new JButton("Закрыть");

        startButton.addActionListener(e -> {
            addNewShape();
            if (animationThread == null || !animationThread.isAlive()) {
                startAnimation();
            }
        });

        closeButton.addActionListener(e -> {
            running = false;
            dispose();
            System.exit(0);
        });

        buttonPanel.add(startButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addNewShape() {
        int panelWidth = drawingPanel.getWidth();
        int panelHeight = drawingPanel.getHeight();
        if (panelWidth == 0) panelWidth = 600; // на случай, если ещё не отрисовано
        if (panelHeight == 0) panelHeight = 400;

        int size = 50;
        int startX = panelWidth - size;
        int startY = panelHeight - size;
        int dx = -2; // влево
        int dy = -2; // вверх

        MovingShape newShape;
        switch (shapeCounter % 3) {
            case 0:
                newShape = new Ball(startX, startY, size, dx, dy, panelWidth, panelHeight);
                break;
            case 1:
                newShape = new Square(startX, startY, size, dx, dy, panelWidth, panelHeight);
                break;
            default:
                newShape = new Star(startX, startY, size, dx, dy, panelWidth, panelHeight);
                break;
        }
        shapeCounter++;
        drawingPanel.addShape(newShape);
    }

    private void startAnimation() {
        animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    // Обновляем позиции всех фигур
                    for (MovingShape shape : drawingPanel.getShapes()) {
                        shape.move();
                    }
                    // Перерисовываем панель в EDT
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            drawingPanel.repaint();
                        }
                    });
                    try {
                        Thread.sleep(30); // ~33 кадра в секунду
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        animationThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShapeAnimationApp().setVisible(true));
    }
}

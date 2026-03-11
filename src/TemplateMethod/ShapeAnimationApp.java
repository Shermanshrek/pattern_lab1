package TemplateMethod;

import javax.swing.*;
import java.awt.*;

public class ShapeAnimationApp extends JFrame {
    private final DrawingPanel drawingPanel;
    private volatile boolean running = true;
    private final JRadioButton ballRadio;
    private final JRadioButton squareRadio;

    public ShapeAnimationApp() {
        setTitle("Прыгающие фигуры");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel, BorderLayout.CENTER);

        // Панель для кнопок выбора и запуска
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Группа радио-кнопок для выбора типа фигуры
        ballRadio = new JRadioButton("Мяч", true); // по умолчанию выбран мяч
        squareRadio = new JRadioButton("Квадрат");
        // радио-кнопки выбора
        JRadioButton starRadio = new JRadioButton("Звезда");

        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(ballRadio);
        shapeGroup.add(squareRadio);
        shapeGroup.add(starRadio);

        controlPanel.add(ballRadio);
        controlPanel.add(squareRadio);
        controlPanel.add(starRadio);

        JButton startButton = new JButton("Пуск");
        JButton closeButton = new JButton("Закрыть");

        startButton.addActionListener(e -> {
            addNewShape(); // добавляем фигуру выбранного типа
        });

        closeButton.addActionListener(e -> {
            running = false;
            dispose();
            System.exit(0);
        });

        controlPanel.add(startButton);
        controlPanel.add(closeButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void addNewShape() {
        int panelWidth = drawingPanel.getWidth();
        int panelHeight = drawingPanel.getHeight();
        if (panelWidth == 0) panelWidth = 600;
        if (panelHeight == 0) panelHeight = 400;

        int size = 50;
        int startX = panelWidth - size;
        int startY = panelHeight - size;
        int dx = -2;
        int dy = -2;

        // Определяем тип фигуры по выбранной радио-кнопке
        MovingShape newShape;
        if (ballRadio.isSelected()) {
            newShape = new Ball(startX, startY, size, dx, dy, panelWidth, panelHeight);
        } else if (squareRadio.isSelected()) {
            newShape = new Square(startX, startY, size, dx, dy, panelWidth, panelHeight);
        } else {
            newShape = new Star(startX, startY, size, dx, dy, panelWidth, panelHeight);
        }

        drawingPanel.addShape(newShape);

        // Запускаем отдельный поток
        Thread shapeThread = new Thread(() -> {
            while (running) {
                newShape.move();
                SwingUtilities.invokeLater(() -> drawingPanel.repaint());
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        shapeThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShapeAnimationApp().setVisible(true));
    }
}
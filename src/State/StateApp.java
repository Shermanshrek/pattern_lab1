package State;

import javax.swing.*;
import java.awt.*;

public class StateApp extends JFrame {
    private Human human;
    private DrawingPanel drawingPanel;

    public StateApp() {
        setTitle("Состояния человечка");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        human = new Human(200, 150);
        drawingPanel.setHuman(human);

        JPanel buttonPanel = new JPanel();
        JButton semesterBtn = new JButton("Семестр");
        JButton holidayBtn = new JButton("Каникулы");
        JButton sessionBtn = new JButton("Сессия");

        semesterBtn.addActionListener(e -> {
            human.setState(new SleepingState());
            drawingPanel.repaint();
        });
        holidayBtn.addActionListener(e -> {
            human.setState(new HappyState());
            drawingPanel.repaint();
        });
        sessionBtn.addActionListener(e -> {
            human.setState(new SadState());
            drawingPanel.repaint();
        });

        buttonPanel.add(semesterBtn);
        buttonPanel.add(holidayBtn);
        buttonPanel.add(sessionBtn);

        add(drawingPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class DrawingPanel extends JPanel {
        private Human human;

        public void setHuman(Human human) {
            this.human = human;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (human != null) {
                Graphics2D g2d = (Graphics2D) g;
                human.draw(g2d);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StateApp().setVisible(true));
    }
}

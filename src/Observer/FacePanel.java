package Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FacePanel extends JPanel implements Observer {
    private List<FacePart> parts = new ArrayList<>();
    private Eye leftEye, rightEye;
    private Nose nose;
    private Mouth mouth;

    public FacePanel() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);

        int centerX = 200, centerY = 200;

        leftEye = new Eye(centerX - 80, centerY - 50, 50, 50);
        rightEye = new Eye(centerX + 30, centerY - 50, 50, 50);
        nose = new Nose(centerX - 20, centerY , 40, 50);
        mouth = new Mouth(centerX - 75, centerY + 50, 150, 40);

        parts.add(leftEye);
        parts.add(rightEye);
        parts.add(nose);
        parts.add(mouth);

        // Подписка панели на изменения всех частей
        for (FacePart part : parts) {
            part.addObserver(this);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                for (FacePart part : parts) {
                    if (part.contains(p)) {
                        part.handleClick();
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Рисуем лицо
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(50, 50, 300, 300);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(50, 50, 300, 300);

        // Рисуем все части лица
        for (FacePart part : parts) {
            part.draw(g2d);
        }
    }

    @Override
    public void update(Observable o) {
        repaint(); // при любом изменении перерисовываем панель
    }
}

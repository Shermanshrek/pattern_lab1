package MVC;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class GraphPanel extends JPanel implements TableModelListener {
    private final FunctionTableModel model;

    public GraphPanel(FunctionTableModel model) {
        this.model = model;
        model.addTableModelListener(this);
        setPreferredSize(new Dimension(500, 400));
        setBackground(Color.WHITE);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();

        // Оси
        g2d.setColor(Color.BLACK);
        g2d.drawLine(50, h - 50, w - 50, h - 50); // X
        g2d.drawLine(50, 50, 50, h - 50);         // Y

        List<Point> points = new ArrayList<>(model.getPoints());
        if (points.isEmpty()) return;

        points.sort(Comparator.comparingDouble(p -> p.x));

        double minX = points.stream().mapToDouble(p -> p.x).min().getAsDouble();
        double maxX = points.stream().mapToDouble(p -> p.x).max().getAsDouble();
        double minY = points.stream().mapToDouble(p -> p.y).min().getAsDouble();
        double maxY = points.stream().mapToDouble(p -> p.y).max().getAsDouble();

        double rangeX = maxX - minX;
        double rangeY = maxY - minY;
        if (rangeX == 0) rangeX = 1;
        if (rangeY == 0) rangeY = 1;
        minX -= 0.1 * rangeX;
        maxX += 0.1 * rangeX;
        minY -= 0.1 * rangeY;
        maxY += 0.1 * rangeY;

        double scaleX = (w - 100) / (maxX - minX);
        double scaleY = (h - 100) / (maxY - minY);

        double finalMinX = minX;
        Function<Double, Integer> transformX = x -> (int) (50 + (x - finalMinX) * scaleX);
        double finalMinY = minY;
        Function<Double, Integer> transformY = y -> (int) ((h - 50) - (y - finalMinY) * scaleY);

        g2d.setColor(Color.BLUE);
        Point prev = null;
        for (Point p : points) {
            int xPix = transformX.apply(p.x);
            int yPix = transformY.apply(p.y);
            g2d.fillOval(xPix - 3, yPix - 3, 6, 6);
            if (prev != null) {
                int xPrev = transformX.apply(prev.x);
                int yPrev = transformY.apply(prev.y);
                g2d.drawLine(xPrev, yPrev, xPix, yPix);
            }
            prev = p;
        }
    }
}

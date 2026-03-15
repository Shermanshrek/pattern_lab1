package MVC;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FunctionTableModel extends AbstractTableModel {
    private final List<Point> points = new ArrayList<>();
    private final String[] columnNames = {"x", "y"};

    private double f(double x) {
        return x * x;
    }

    public void addPoint(double x) {
        points.add(new Point(x, f(x)));
        int row = points.size() - 1;
        fireTableRowsInserted(row, row);
    }

    public void removePoint(int index) {
        if (index >= 0 && index < points.size()) {
            points.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public int getRowCount() {
        return points.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Point p = points.get(rowIndex);
        return columnIndex == 0 ? p.x : p.y;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            try {
                double newX = Double.parseDouble(aValue.toString());
                points.get(rowIndex).x = newX;
                points.get(rowIndex).y = f(newX);
                fireTableRowsUpdated(rowIndex, rowIndex);
            } catch (NumberFormatException e) {

            }
        }
    }
}

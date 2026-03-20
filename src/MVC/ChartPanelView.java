package MVC;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.util.List;

public class ChartPanelView extends ChartPanel implements TableModelListener {
    private final FunctionTableModel model;
    private final XYSeries series;

    public ChartPanelView(FunctionTableModel model) {
        super(createChart(model));
        this.model = model;
        this.series = new XYSeries(model.getFormula());
        model.addTableModelListener(this);
        updateDataset();
    }

    private static JFreeChart createChart(FunctionTableModel model) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График функции",
                "x",              // подпись оси X
                "y",              // подпись оси Y
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chart.setBackgroundPaint(Color.WHITE);
        return chart;
    }

    private void updateDataset() {
        series.clear();
        List<Point> points = model.getPoints();
        for (Point p : points) {
            series.add(p.x, p.y);
        }
        XYSeriesCollection dataset = (XYSeriesCollection) getChart().getXYPlot().getDataset();
        dataset.removeAllSeries();
        dataset.addSeries(series);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        updateDataset();
    }
}

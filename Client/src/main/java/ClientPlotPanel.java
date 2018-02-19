import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Class ClientPlotPanel
 * Will maintain a plotting area using recieved data
 *
 * @author team02
 * @see javax.swing.JPanel
 */
public class ClientPlotPanel {

    private static ChartPanel chartPanel;

    /**
     * Constructor
     */
    ClientPlotPanel(){
        //Create Line Chart
        JFreeChart lineChart = ChartFactory.createLineChart(
          "test",
          "Time", "Value",
           createDataset(),
           PlotOrientation.VERTICAL,
           true,
           true,
           false
        );

        //save it into chartPanel
        chartPanel = new ChartPanel(lineChart);
    }

    /**
     * Create a temporary dataset to test the functionality of plot panel.
     */
    private DefaultCategoryDataset createDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        return dataset;
    }

    /**
     * Entry point to get chartPanel
     * @return ChartPanel created
     */
    public ChartPanel getChartPanel(){
        return chartPanel;
    }
}

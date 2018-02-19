import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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

        //Randomly generate 20 test data
        //Generate current time
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

        Random rand = new Random();

        for(int i = 0; i < 20; i++){
            //Add 1s for each value's time
            cal.add(Calendar.SECOND, 5);
            //Generate a random value
            dataset.addValue(rand.nextInt(200) + 1, "Channel 1", String.valueOf(sdf.format(cal.getTime())));
        }

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

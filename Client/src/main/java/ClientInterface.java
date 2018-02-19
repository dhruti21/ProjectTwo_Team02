import org.jfree.chart.ChartPanel;

import javax.swing.JFrame;

/**
 * Client Interface.
 *
 * @author team2
 * @version 1.0
 */
public class ClientInterface extends JFrame {

    /**
     * Constructor of class ClientInterface
     */
    public ClientInterface() {
        super();

        //Setting size and titles
        setTitle("Client");
        setBounds(200, 200, 500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //TODO: Other Client UI Components
        //TODO: Set Good Layout of Client UI

        //Plotting panel
        ChartPanel chartPanel = new ClientPlotPanel().getChartPanel();
        setContentPane(chartPanel);
    }

}

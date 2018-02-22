/*
Testing plotting panel, should be deleted when release.
 */

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TestClientPlotPanel {

    public static final String IP = "localhost";
    public static final int PORT = 3000;
    public static final int DEFAULT_CHANNEL_NUM = 1;

    private static final int CONNECTION_TIMEOUT = 5000;

    private static TestClientPlotPanel sApp;

    private Client mClientConnection;
    private TestFrame mTestFrame;
    private  ClientStatsManager mStatsMgr;

    private class TestFrame extends JFrame {

        private ClientPlotPanel clientPlotPanel;

        TestFrame(){
            super();
            setTitle("Test Client Plot Panel");
            setBounds(200, 200, 500, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Plot panel
            clientPlotPanel = new ClientPlotPanel();
            setContentPane(clientPlotPanel.getChartPanel());
        }

        public ClientPlotPanel getClientPlotPanel(){ return clientPlotPanel;}


    }

    public void init() {
        try {
            connectToServer();
            mTestFrame = new TestFrame();
            mTestFrame.setVisible(true);
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void connectToServer() throws IOException {
        mClientConnection = new Client();
        mClientConnection.start();
        mClientConnection.connect(CONNECTION_TIMEOUT, IP, PORT);

        Network.register(mClientConnection);

        ConnectionOpen newConnection = new ConnectionOpen( DEFAULT_CHANNEL_NUM );
        mClientConnection.sendTCP( newConnection );

        mStatsMgr = new ClientStatsManager();
        mStatsMgr.init();

        mClientConnection.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof Frequency) {
                    int frequency = ((Frequency) object ).getFrequency();
                    System.out.println( "Frequency set to: " + frequency + " Hz" );
                    // TODO FOR CLIENT TEAM - Save frequency on client side for displaying

                } else if( object instanceof Channels ){
                    ArrayList<ChannelNumber> channelList = ( (Channels) object ).getChannelList();
                    for( ChannelNumber channelNum : channelList ){
                        int channel = channelNum.getChannel();
                        int data = channelNum.getNumber();
                        System.out.println( "Channel: " + channel + ", Data: " + data );
                        //Get current time
                        Date cal = Calendar.getInstance().getTime();
                        //Add data into plot dataset
                        mTestFrame.getClientPlotPanel().addData(cal, channel, data);
                        mStatsMgr.onReceiveData(channel, data);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        sApp = new TestClientPlotPanel();
        sApp.init();
    }
}

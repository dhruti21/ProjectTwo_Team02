import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.management.loading.MLetContent;

/**
 * Client
 *
 * <P>Client side application entry point.
 * <P>This establishes a connection to the server.
 *
 * @author Team 2
 * @version 1.0
 */
public class ClientApp {

    public static final String IP = "localhost";
    public static final int PORT = 3000;
    public static final int DEFAULT_CHANNEL_NUM = 3;

    private static final int CONNECTION_TIMEOUT = 5000;

    private static ClientApp instance;

    private  Client clientConnection;
    private ClientInterface clientInterface;
    private  ClientStatsManager statsMgr;
    private int curChannel;

    public static ClientApp getInstance() {
        if (instance == null) {
            instance = new ClientApp();
        }
        return instance;
    }

    public void init() {
        try {
            curChannel = 1;
            clientInterface = new ClientInterface(DEFAULT_CHANNEL_NUM);
            clientInterface.getFromClient().setVisible(true);
            addChannelChangeListener();
            connectToServer();
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void connectToServer() throws IOException {
        clientConnection = new Client();
        clientConnection.start();
        clientConnection.connect(CONNECTION_TIMEOUT, IP, PORT);

        Network.register(clientConnection);

        ConnectionOpen newConnection = new ConnectionOpen( DEFAULT_CHANNEL_NUM );
        clientConnection.sendTCP( newConnection );

        statsMgr = new ClientStatsManager();
        statsMgr.init();

        clientConnection.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof Frequency) {
                    int frequency = ((Frequency) object ).getFrequency();
                    System.out.println( "Frequency set to: " + frequency + " Hz" );
                    clientInterface.setFrequency(frequency);

                } else if( object instanceof Channels ){
                    ArrayList<ChannelNumber> channelList = ( (Channels) object ).getChannelList();
                    for( ChannelNumber channelNum : channelList ){
                        int channel = channelNum.getChannel();
                        int data = channelNum.getNumber();
                        System.out.println( "Channel: " + channel + ", Data: " + data );
                        //Get current time
                        Date cal = Calendar.getInstance().getTime();
                        //Add data into plot dataset
                        clientInterface.getClientPlotPanel().addData(cal, channel, data);
                        statsMgr.onReceiveData(channel, data);
                    }
                    UpdateInterfaceStats();
                }
            }
        });
    }

    private void addChannelChangeListener() {
        clientInterface.setChannelChangeListener(new ClientInterface.ChannelChangeListerner(){
            @Override
            public void onChannelChange(int channel) {
                curChannel = channel;
                UpdateInterfaceStats();
            }
        });
    }

    private void UpdateInterfaceStats() {
        clientInterface.setAverageValue(statsMgr.getAverageValue(curChannel));
        clientInterface.setLowestValue(statsMgr.getLowestValue(curChannel));
        clientInterface.setHighestValue(statsMgr.getHighestValue(curChannel));
    }

    public static void main(String[] args) {
        getInstance().init();
    }
}

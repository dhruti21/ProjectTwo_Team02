import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;
import java.io.IOException;

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

    private static final int CONNECTION_TIMEOUT = 5000;

    private static Client ClientConnection;
    private static ClientInterface clientInterface;

    private static void connectToServer() throws IOException {
        ClientConnection = new Client();
        ClientConnection.start();
        ClientConnection.connect(CONNECTION_TIMEOUT, IP, PORT);

        Network.register(ClientConnection);

        Message request = new Message();
        request.text = "Message from client";
        ClientConnection.sendTCP(request);

        ClientConnection.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof Message) {
                    Message response = (Message)object;
                    System.out.println(response.text);
                }
            }
        });
    }

    public static void main(String[] args) {

        //Connect to Server
        try {
            connectToServer();
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        //Start Client UI
        try {
            clientInterface = new ClientInterface();
            clientInterface.setVisible(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }

    }

}

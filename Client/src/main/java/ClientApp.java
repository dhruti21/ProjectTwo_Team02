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

    public static void main( String[] args ) throws IOException {
        Client client = new Client();
        client.start();
        client.connect(5000, IP, PORT);

        Network.register( client );

        Message request = new Message();
        request.text = "Message from client";
        client.sendTCP(request);

        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof Message) {
                    Message response = (Message)object;
                    System.out.println(response.text);
                }
            }
        });

        // TODO - Remove when UI is added.
        while(true){}
    }
}

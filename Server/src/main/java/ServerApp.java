import java.io.IOException;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;

/**
 * Server
 *
 * <P>Server side application entry point.
 * <P>This opens a server on the current machine.
 *
 * @author Team 2
 * @version 1.0
 */
public class ServerApp {

    public Server server;
    public static final int PORT = 3000;

    private void startServer() throws IOException{
        server = new Server();
        server.start();
        server.bind( PORT );

        Network.register( server );

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof Message) {
                    Message request = (Message)object;
                    System.out.println(request.text);

                    Message response = new Message();
                    response.text = "Message from server";
                    connection.sendTCP(response);
                }
            }
        });
    }

    public static void main( String[] args ) {

        try {
            ServerApp serverApp = new ServerApp();
            serverApp.startServer();
            ServerInterface window = new ServerInterface();
            window.getFrmServer().setVisible(true);
        } catch (IOException e){
            System.out.println("Error creating server");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

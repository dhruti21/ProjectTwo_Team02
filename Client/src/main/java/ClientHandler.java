/**
 * Handles sending and receiving data for the client.
 *
 * @author Team2
 * @version 1.0
 */
public class ClientHandler {

    private static ClientHandler handler;
    private volatile boolean clientRecieveStatus = true;

    public static ClientHandler getInstance(){
        if( handler == null ){
            handler = new ClientHandler();
        }
        return handler;
    }

    public boolean getClientSendStatus(){
        return clientRecieveStatus;
    }

    public void setClientSendStatus( boolean sendStatus ){
            //TODO: need to do logic for start and stop of server
        clientRecieveStatus = sendStatus;
    }
}

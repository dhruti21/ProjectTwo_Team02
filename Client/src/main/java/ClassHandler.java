/**
 * Handles sending and receiving data for the client.
 *
 * @author Team2
 * @version 1.0
 */
public class ClassHandler {
    private static ClassHandler handler;

    public static ClassHandler getInstance(){
        if( handler == null ){
            handler = new ClassHandler();
        }
        return handler;
    }
}

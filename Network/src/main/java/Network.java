
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

/**
 * Network
 *
 * <P>Responsible for registering all classes of objects that will be sent through
 * the network. Either to/from client or server.
 *
 * @author Team 2
 * @version 1.0
 */
public class Network {
    public static void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(Message.class);
    }
}

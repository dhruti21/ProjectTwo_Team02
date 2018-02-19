import java.nio.channels.Channel;
import java.util.ArrayList;

/**
 * Client Stats Manager for all stats processing
 *
 * @author Team 2
 * @version 1.0
 */
public class ClientStatsManager {

    private ArrayList<StatsInterface> mStatsList;
    private ClientHighestStats mHighest;

    public void init() {
        mStatsList = new ArrayList<StatsInterface>();
        mHighest = new ClientHighestStats();
        mStatsList.add(mHighest);
    }
    
    public void OnReceiveData(int channel, int data) {
        for (StatsInterface i : mStatsList) {
            i.onReceiveData(channel, data);
        }
    }

    public int getHighestValue(int channel) {
        return mHighest.getStats(channel);
    }
}
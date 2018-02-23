package client;

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
    private ClientLowestStats mLowest;
    private ClientAverageStats mAverage;

    public void init() {
        mStatsList = new ArrayList<StatsInterface>();
        mHighest = new ClientHighestStats();
        mLowest = new ClientLowestStats();
        mAverage = new ClientAverageStats();
        mStatsList.add(mHighest);
        mStatsList.add(mLowest);
        mStatsList.add(mAverage);
    }
    
    public void onReceiveData(int channel, int data) {
        for (StatsInterface i : mStatsList) {
            i.onReceiveData(channel, data);
        }
    }

    public int getHighestValue(int channel) {
        return mHighest.getStats(channel);
    }

    public int getLowestValue(int channel) {
        return mLowest.getStats(channel);
    }

    public int getAverageValue(int channel) {
        return mAverage.getStats(channel);
    }
}
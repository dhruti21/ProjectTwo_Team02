package client;

import java.util.HashMap;

/**
 * Receive data from the server and calculate the lowest 
 * value of each channel
 *
 * @author Team 2
 * @version 1.0
 */
public class ClientLowestStats implements StatsInterface {
    
    private HashMap<Integer, Integer> dataContainer =
        new HashMap<Integer, Integer>();

    @Override
    public void onReceiveData(int channel, int data) {
        int cur = getValue(channel);
        cur = Math.min(cur, data);
        dataContainer.put(channel, cur);
    }

    /**
     * @param channelNumber 
     * @return the lowest value of the channel
    */
    @Override
    public int getValue(int channelNumber) {
        if (!dataContainer.containsKey(channelNumber)) {
            dataContainer.put(channelNumber, Integer.MAX_VALUE);
        }
        return dataContainer.get(channelNumber);
    }
}
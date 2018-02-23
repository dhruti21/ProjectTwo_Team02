package client;

import java.util.HashMap;

/**
 * Receive data from the server and calculate the highest 
 * value of each channel
 * 
 * @author Team 2
 * @version 1.0
 */
public class ClientHighestStats implements StatsInterface {

    private HashMap<Integer, Integer> dataContainer = new HashMap<Integer, Integer>();

    @Override
    public void onReceiveData(int channel, int data) {
        int cur = getValue(channel);
        cur = Math.max(cur, data);
        dataContainer.put(channel, cur);
    }

    /**
     * @param channelIndex 
     * @return the highest value of the channel
    */
    @Override
    public int getValue(int channelIndex) {
        if (!dataContainer.containsKey(channelIndex)) {
            dataContainer.put(channelIndex, Integer.MIN_VALUE);
        }
        return dataContainer.get(channelIndex);
    }
}
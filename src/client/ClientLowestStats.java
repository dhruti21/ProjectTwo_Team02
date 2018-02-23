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
    
	/*
     * What is "hash"? Rename to what it holds --MD
     */
    private HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();

    @Override
    public void onReceiveData(int channel, int data) {
        int cur = getValue(channel);
        cur = Math.min(cur, data);
        hash.put(channel, cur);
    }

    /**
     * @param channelNumber 
     * @return the lowest value of the channel
    */
    @Override
    public int getValue(int channelNumber) {
        if (!hash.containsKey(channelNumber)) {
            hash.put(channelNumber, Integer.MAX_VALUE);
        }
        return hash.get(channelNumber);
    }
}
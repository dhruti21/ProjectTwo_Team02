package client;

/**
 * An interface for unified data processing
 * It receive data from server and calculate a specific value of
 * each channel. The specific value can be average, the highest, etc.
 *
 * @author Team 2
 * @version 1.0
 */
public interface StatsInterface {

    /**
     * receive data from the server and produce values like
     * average, the highest, etc.
     * 
     * @param channelIndex
     * @param channelInputData 	integer received from a channel
    */
    public void onReceiveData(int channelIndex, int channelInputData);

    /**
     * @param channelIndex 
     * @return stats for a given channel. 
     * It can be average, the highest, etc.
    */
    public int getValue(int channelIndex);
}
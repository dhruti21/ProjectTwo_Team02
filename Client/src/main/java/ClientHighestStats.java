import java.util.HashMap;

/**
 * Returning the highest value in a given channel
 *
 * @author Team 2
 * @version 1.0
 */
public class ClientHighestStats implements StatsInterface {
	private HashMap<Integer, Integer> mHash = new HashMap<Integer, Integer>();
	
	@Override
	public void onReceiveData(int channel, int data) {
        int cur = getStats(channel);
        cur = Math.max(cur, data);
        mHash.put(channel, cur);
	}

	@Override
	public int getStats(int channel) {
		if (!mHash.containsKey(channel)) {
            mHash.put(channel, Integer.MIN_VALUE);
        }
        return mHash.get(channel);
	}
}
import java.awt.SystemTray;
import java.util.HashMap;

/**
 * Returning the average value in a given channel
 *
 * @author Team 2
 * @version 1.0
 */
public class ClientAverageStats implements StatsInterface {
	private HashMap<Integer, InnerData> mHash = new HashMap<Integer, InnerData>();
	
	private class InnerData {
		int sum = 0;
		int num = 0;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void onReceiveData(int channel, int data) {
        InnerData cur = getData(channel);
		cur.sum += data;
		cur.num += 1;
	}

	@Override
	public int getStats(int channel) {
		InnerData d = getData(channel);
		int average = d.sum / d.num;
        return average;
	}

	private InnerData getData(int channel) {
		if (!mHash.containsKey(channel)) {
            mHash.put(channel, new InnerData());
		}
		return mHash.get(channel);
	}
}
import java.awt.SystemTray;
import java.util.HashMap;

/**
 * Returning the average value in a given channel
 *
 * @author Team 2
 * @version 1.0
 */
public class ClientAverageStats implements StatsInterface {	
	private class InnerData {
		int sum = 0;
		int num = 0;
	}

	private HashMap<Integer, InnerData> hash = new HashMap<Integer, InnerData>();

	@Override
	public void onReceiveData(int channel, int data) {
        InnerData cur = getData(channel);
		cur.sum += data;
		cur.num += 1;
	}

	@Override
	public int getStats(int channel) {
		InnerData d = getData(channel);
		if (d.num == 0) {
			return 0;
		} else {
			return d.sum / d.num;
		}
	}

	private InnerData getData(int channel) {
		if (!hash.containsKey(channel)) {
            hash.put(channel, new InnerData());
		}
		return hash.get(channel);
	}
}
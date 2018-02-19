
/**
 * An interface for stats processing
 *
 *
 * @author Team 2
 * @version 1.0
 */
public interface StatsInterface {
    public void init();
    
    public void onReceiveData(int channel, int data);

    public int getStats(int channel);
}
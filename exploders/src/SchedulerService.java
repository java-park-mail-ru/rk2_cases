import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Solovyev on 09/04/2017.
 */
public class SchedulerService {

    private AtomicLong serverTime = new AtomicLong(0);

    private TreeMap<Long, Job> awaitingJobs = new TreeMap<>();

    public void gmTick(long frameMillis) {
        long currentTime = serverTime.addAndGet(frameMillis);
        SortedMap<Long, Job> jobsToExecute = awaitingJobs.tailMap(currentTime);
        for (Map.Entry<Long, Job> longJobEntry : jobsToExecute.entrySet()) {
           //some code here
        }
        System.out.println("tick-tock. Time is " + serverTime);
    }

    public void submit(Job job, long timestamp) {
        //some code here
    }

    public long getServerTime() {
        return serverTime.get();
    }
}

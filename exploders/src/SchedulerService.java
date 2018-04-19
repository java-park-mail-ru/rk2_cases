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
        // headMap - return a view of the portion of this map whose keys are strictly less than currentTime.
        SortedMap<Long, Job> jobsToExecute = awaitingJobs.headMap(currentTime);
        for (Map.Entry<Long, Job> longJobEntry : jobsToExecute.entrySet()) {
            try {
                longJobEntry.getValue().execute();
            }
            catch (Exception e) {
                System.out.println("OMG, Job's execute method possibly is not overridden!");
                e.printStackTrace();
            }
            awaitingJobs.remove(longJobEntry.getKey());
        }
        System.out.println("tick-tock. Time is " + serverTime);
    }

    public void submit(Job job, long timestamp) {
        awaitingJobs.put(timestamp, job);
        System.out.println("new job added. It will be executed in " + timestamp + " milliseconds.");
    }

    public long getServerTime() {
        return serverTime.get();
    }
}

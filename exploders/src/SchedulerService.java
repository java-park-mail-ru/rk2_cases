import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Solovyev on 09/04/2017.
 */
public class SchedulerService {

    private AtomicLong serverTime = new AtomicLong(0);

    private TreeMap<Long, Job> awaitingJobs = new TreeMap<Long, Job>();

    public void gmTick(long frameMillis) {
        long currentTime = serverTime.addAndGet(frameMillis);
        SortedMap<Long, Job> jobsToExecute = awaitingJobs.headMap(currentTime);
        for (Map.Entry<Long, Job> longJobEntry : jobsToExecute.entrySet()) {
            awaitingJobs.remove(longJobEntry.getKey());
            try {
                longJobEntry.getValue().execute();
            } catch (Exception e) {
                System.out.println("Can't execute this job");
            }
        }
        System.out.println("tick-tock. Time is " + serverTime);
    }

    public void submit(Job job, long timestamp) {
        awaitingJobs.put(timestamp, job);
    }

    public long getServerTime() {
        return serverTime.get();
    }
}

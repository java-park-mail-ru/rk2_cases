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
//        SortedMap<Long, Job> jobsToExecute = awaitingJobs.tailMap(currentTime);
        SortedMap<Long, Job> jobsToExecute = awaitingJobs.subMap((long) 0, currentTime);
        for (Map.Entry<Long, Job> longJobEntry : jobsToExecute.entrySet()) {
            //some code here
            long time = longJobEntry.getKey();
            Job job = longJobEntry.getValue();
            try {
                job.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            awaitingJobs.remove(time);
        }
        System.out.println("tick-tock. Time is " + serverTime);
    }

    public void submit(Job job, long timestamp) {
        //some code here
        awaitingJobs.put(timestamp + getServerTime(), job);
    }

    public long getServerTime() {
        return serverTime.get();
    }
}

/**
 * Created by Solovyev on 09/04/2017.
 */

public class Bomb implements Job {

    public Bomb(SchedulerService schedulerService, long bombTimer) {
        // your code here
        schedulerService.submit(this, schedulerService.getServerTime() + bombTimer);
        System.out.println("Bomb has set. It should explode after " + bombTimer + " milliseconds!");
    }

    public void explode() {
        System.out.println("Bomb has exploded!");
    }

    @Override
    public void execute() {
        explode();
    }
}

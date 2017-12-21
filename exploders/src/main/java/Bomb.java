/**
 * Created by Solovyev on 09/04/2017.
 */
public class Bomb implements Job {

    public Bomb(SchedulerService schedulerService, long bombTimer) {
        schedulerService.submit(this, bombTimer + schedulerService.getServerTime());
        System.out.println("Bomb has set. It should explode after " + bombTimer + " seconds!");
    }

    @Override
    public void execute() {
        System.out.println("Bomb has exploded!");
    }
}

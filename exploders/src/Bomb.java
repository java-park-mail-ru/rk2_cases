/**
 * Created by Solovyev on 09/04/2017.
 */
public class Bomb {

    public Bomb(SchedulerService schedulerService, long bombTimer) {
        Job explosion = this::explode;
        schedulerService.submit(explosion, bombTimer);
        System.out.println("Bomb has set. It should explode after " + bombTimer + " seconds!");
    }

    public void explode() {
        System.out.println("Bomb has exploded!");
    }
}

/**
 * Created by Solovyev on 09/04/2017.
 */
public class ExplosionScenario {

    public static void main(String[] args) {
        final SchedulerService schedulerService = new SchedulerService();
        schedulerService.gmTick(1000);
        new Bomb(schedulerService, 5000);
        schedulerService.gmTick(1500);
        schedulerService.gmTick(1500);
        new Bomb(schedulerService, 5500);
        schedulerService.gmTick(1000);
        schedulerService.gmTick(1500);
        schedulerService.gmTick(1500);
        schedulerService.gmTick(3000);
    }
}

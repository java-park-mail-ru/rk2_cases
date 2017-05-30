/**
 * Created by Solovyev on 09/04/2017.
 */
public class Bomb {

    public Bomb(SchedulerService schedulerService, long bombTimer) {
        // your code here
        schedulerService.submit(new Job() {
								@Override
								public void execute() throws Exception {
									explode();
								}
							},
		bombTimer);
        System.out.println("Bomb has set. It should explode after " + bombTimer + "!");
    }

    public void explode() {
        System.out.println("Bomb has exploded!");
    }
}

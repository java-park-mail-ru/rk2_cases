public class Bomb{

    public Bomb(final SchedulerService schedulerService, final long bombTimer) {
        schedulerService.submit(new Job() {
            public void execute() throws Exception {
                explode();
            }
        }, bombTimer + schedulerService.getServerTime());
        System.out.println("Bomb has set. It should explode after " + bombTimer + " seconds!");
    }

    public void explode() {
        System.out.println("Bomb has exploded!");
    }
}

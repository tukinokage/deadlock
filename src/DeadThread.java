public class DeadThread {
    public Thread thread;
    public void  start() throws InterruptedException {
        thread = new Thread(new RealRunnable(), "死锁");
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }


    private class RealRunnable implements Runnable{

        @Override
        public void run() {
            try {
                //先执行该join
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package amery.jdk.concurrent.lock.redis;

/**
 * @author ameryhan
 * @date 2019/8/21 20:32
 */
public class Test001 {

    public static void main(String[] args) {
        LockService lockService = new LockService();
        for (int i = 0; i < 50; i++) {
            ThreadRedis threadRedis = new ThreadRedis(lockService);
            threadRedis.start();
        }
    }

    static class ThreadRedis extends Thread {
        private LockService lockService;

        public ThreadRedis(LockService lockService) {
            this.lockService = lockService;
        }

        @Override
        public void run() {
            lockService.seckill();

        }


    }
}

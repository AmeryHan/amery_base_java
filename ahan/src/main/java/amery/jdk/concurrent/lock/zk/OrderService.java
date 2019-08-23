package amery.jdk.concurrent.lock.zk;

/**
 * @author ameryhan
 * @date 2019/8/23 16:24
 */
public class OrderService implements Runnable {
    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
    // 使用lock锁
    // private java.util.concurrent.locks.Lock lock = new ReentrantLock();
    private Lock lock = new ZookeeperDistrbuteLock();

    @Override
    public void run() {
        getNumber();
    }

    public void getNumber() {
        try {
            lock.getLock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + ",生成订单ID:" + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unLock();
        }
    }

    public static void main(String[] args) {
        System.out.println("####生成唯一订单号###");
//		OrderService orderService = new OrderService();
        for (int i = 0; i < 100; i++) {
            new Thread(new OrderService()).start();
        }
    }

}

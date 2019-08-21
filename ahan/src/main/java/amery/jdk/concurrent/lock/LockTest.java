package amery.jdk.concurrent.lock;

/**
 * @author ameryhan
 * @date 2019/8/21 12:44
 */
public class LockTest implements Runnable{

    public static void main(String[] args) {
        LockTest ss = new LockTest();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }

    public  synchronized void get() {
        System.out.println("name:" + Thread.currentThread().getName() + " get();");
        set();
    }

    public synchronized  void set() {
        System.out.println("name:" + Thread.currentThread().getName() + " set();");
    }

    //重入锁，也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。
    @Override
    public void run() {
        get();
    }

}

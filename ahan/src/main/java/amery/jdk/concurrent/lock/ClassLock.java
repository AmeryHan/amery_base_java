package amery.jdk.concurrent.lock;

/**
 * @author ameryhan
 * @date 2019/9/6 11:12
 */
public class ClassLock {

    synchronized public static void printA() {
        try {
            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在  " + System.currentTimeMillis() + " 进入pringA()");
            Thread.sleep(9000);
            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在  " + System.currentTimeMillis() + " 离开pringA()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public static void printB() {
        try {
            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在  " + System.currentTimeMillis() + " 进入printB()");

            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在  " + System.currentTimeMillis() + " 离开printB()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void printC() {
        try {
            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在  " + System.currentTimeMillis() + " 进入printC()");

            System.out.println("线程名称为:" + Thread.currentThread().getName() + "在  " + System.currentTimeMillis() + " 离开printC()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClassLock s = new ClassLock();
        ThreadA a = new ThreadA(s);
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB(s);
        b.setName("B");
        b.start();

        ThreadC c = new ThreadC(s);
        c.setName("C");
        c.start();

    }
}

class ThreadA extends Thread {
    private ClassLock s;

    public ThreadA(ClassLock s) {
        super();
        this.s = s;
    }

    @Override
    public void run() {
        ClassLock.printA();
    }
}


class ThreadB extends Thread {
    private ClassLock s;

    public ThreadB(ClassLock s) {
        super();
        this.s = s;
    }

    @Override
    public void run() {
        ClassLock.printA();
    }
}


class ThreadC extends Thread {
    private ClassLock s;

    public ThreadC(ClassLock s) {
        super();
        this.s = s;
    }

    @Override
    public void run() {
        ClassLock.printA();
    }
}

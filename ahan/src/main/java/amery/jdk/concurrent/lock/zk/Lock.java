package amery.jdk.concurrent.lock.zk;

/**
 * @author ameryhan
 * @date 2019/8/23 16:08
 */
public interface Lock {
    //获取到锁的资源
    void getLock();

    // 释放锁
    void unLock();

}

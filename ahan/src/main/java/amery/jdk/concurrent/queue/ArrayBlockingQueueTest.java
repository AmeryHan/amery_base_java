package amery.jdk.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author ameryhan
 * @date 2019/8/21 11:22
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) throws Exception {
        //有边界的阻塞队列
        ArrayBlockingQueue<String> arrays = new ArrayBlockingQueue<String>(3);
        arrays.add("李四");
        arrays.add("张军");
        arrays.add("张军");
        // 添加阻塞队列
        arrays.offer("张三", 1, TimeUnit.SECONDS);
        System.out.printf("arrays");
    }
}

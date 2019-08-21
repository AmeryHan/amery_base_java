package amery.jdk.concurrent.queue;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author ameryhan
 * @date 2019/8/21 11:18
 */
public class ConcurrentLinkedDequeTest {

    public static void main(String[] args) {
        ConcurrentLinkedDeque q = new ConcurrentLinkedDeque();
        q.offer("余胜军");
        q.offer("码云");
        q.offer("蚂蚁课堂");
        q.offer("张杰");
        q.offer("艾姐");
        //从头获取元素,删除该元素
        System.out.println(q.poll());
        //从头获取元素,不刪除该元素
        System.out.println(q.peek());
        //获取总长度
        System.out.println(q.size());
    }
}

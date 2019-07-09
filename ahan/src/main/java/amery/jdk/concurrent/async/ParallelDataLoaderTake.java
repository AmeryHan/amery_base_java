package amery.jdk.concurrent.async;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并行数据加载器
 *
 * @author 小马哥
 * @since 2018/6/20
 */
public class ParallelDataLoaderTake extends DataLoader {

    protected void doLoad() {  // 并行计算
        ExecutorService executorService = Executors.newFixedThreadPool(3); // 创建线程池
        CompletionService completionService = new ExecutorCompletionService(executorService);
        completionService.submit(super::loadConfigurations, null);      //  耗时 >= 1s
        completionService.submit(super::loadUsers, null);               //  耗时 >= 2s
        completionService.submit(super::loadOrders, null);              //  耗时 >= 3s

        executorService.shutdown();

        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("此次任务的结结:"+completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }  // 总耗时 max(1s, 2s, 3s)  >= 3s

    public static void main(String[] args) {
        new ParallelDataLoaderTake().load();
    }

}

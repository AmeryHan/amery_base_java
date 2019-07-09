package amery.jdk.concurrent.async;

import java.util.concurrent.*;

/**
 * 并行数据加载器
 *
 * @author 小马哥
 * @since 2018/6/20
 */
public class ParallelDataLoaderV2Poll extends DataLoaderV2 {

    @Override
    protected void doLoad() {  // 并行计算
        ExecutorService executorService = Executors.newFixedThreadPool(3); // 创建线程池
        CompletionService completionService = new ExecutorCompletionService(executorService);

        completionService.submit(() -> loadConfigurations());
        completionService.submit(() -> loadUsers());
        completionService.submit(this::loadOrders);

        int count = 0;
        while (count < 3) { // 等待三个任务完成
            Future future = completionService.poll();
            if (future != null) {
                try {
                    System.out.println("此次任务执行的结果"+future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                count++;
            }
        }
        executorService.shutdown();
        //https://cloud.tencent.com/developer/article/1444259
    }  // 总耗时 max(1s, 2s, 3s)  >= 3s

    public static void main(String[] args) {
        new ParallelDataLoaderV2Poll().load();
    }

}

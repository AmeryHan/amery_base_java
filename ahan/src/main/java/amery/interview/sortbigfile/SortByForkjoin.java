package amery.interview.sortbigfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class SortByForkjoin {

    private static final String filePath="/Users/ameryhan/github/amery_base_java/data1.txt";
    private static final String afterFilePath="/Users/ameryhan/github/amery_base_java/data1-sort.txt";

    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool();

        /***
         * 1.从文件中读取内存容量可处理的数据量 ,拆分为多个部分排序的文件
         * 2.每100_000条数据为一个文件
         * */
        int size = 100_000;
        int[] array = new int[size];
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = null;
        int i = 0;
        int partition = 0;

        /***
         * 1.拆分的部分文件名list，后面归并部分文件需要用到它
         * 2.每size条数据，排序后，放到一个文件
         * */
        List<String> partFiles = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            array[i++] = Integer.parseInt(line);
            if (i == size) {
                // 重置i为0
                i = 0;
                // 排序输出到部分文件
                String filename = "/Users/ameryhan/github/amery_base_java/data1-part-" + partition++ + ".txt";
                //对当前partition的数据进行排序
                doPartitionSort(pool, filename, array, 0, size);
                partFiles.add(filename);
            }
        }
        reader.close();

        // 未达到size的部分数据，排序后放一个文件
        // 经debug并没有进去此段代码 - ameryhan
        if (i > 0) {
            // 排序输出到部分文件
            String filename = "/Users/ameryhan/github/amery_base_java/data1-part-" + partition++ + ".txt";
            doPartitionSort(pool, filename, array, 0, i);
            partFiles.add(filename);
        }

        if (partition > 1) {
            // 归并排序
            MergerFileSortTask mtask = new MergerFileSortTask(partFiles, afterFilePath);
            pool.submit(mtask);
            mtask.get();

        } else {
            // 将唯一的一个部分文件重命名为最终结果文件名
        }
        pool.shutdown();
    }

    /**
     * partition的数据进行排序
     * @param pool  工作池
     * @param filename  文件名
     * @param array  数组
     * @param start  开始下标
     * @param end   结束下标
     * @throws Exception
     */
    static void doPartitionSort(ForkJoinPool pool, String filename, int[] array, int start, int end) throws Exception {
        ArrayMergerSortTask task = new ArrayMergerSortTask(array, start, end);
        pool.submit(task);
        task.get();
        try (PrintWriter pw = new PrintWriter(filename)) {
            for (int i = start; i < end; i++) {
                pw.println(array[i]);
            }
        }
    }
}

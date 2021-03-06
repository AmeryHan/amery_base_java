package amery.interview.sortbigfile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateNumber {

    private static final String filePath = "/Users/ameryhan/github/amery_base_java/data1.txt";

    public static void main(String[] args) throws IOException {
        //随机生成数据
        Random random = new Random();
        try (PrintWriter out = new PrintWriter(new File(filePath))) {
            long beginTime = System.currentTimeMillis();
            System.out.println("beginTime:" + beginTime);
            for (int i = 0; i < 1_000_000; i++) {
                out.println(random.nextInt());
                if (i % 10000 == 0)
                    out.flush();
            }
            System.out.println("endTime:" + (System.currentTimeMillis() - beginTime));
        }
    }
}

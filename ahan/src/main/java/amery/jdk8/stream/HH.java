package amery.jdk8.stream;

import java.lang.annotation.ElementType;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class HH {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            double d = Math.random() * 1000;
            list.add(d + "");
        }
        long start = System.nanoTime();//获取系统开始排序的时间点
        int count = (int) ((Stream) list.stream().sequential()).sorted().count();
        long end = System.nanoTime();//获取系统结束排序的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end - start);//得到串行排序所用的时间
        System.out.println(ms + "ms");
    }

    public static void main1(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            double d = Math.random() * 1000;
            list.add(d + "");
        }
        long start = System.nanoTime();//获取系统开始排序的时间点
        int count = (int) ((Stream) list.stream().parallel()).sorted().count();
        long end = System.nanoTime();//获取系统结束排序的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end - start);//得到并行排序所用的时间
        System.out.println(ms + "ms");
        //串行输出为 1200ms，并行输出为 800ms。可见，并行排序的时间相比较串行排序时间要少不少。

        list.stream() //获取列表的 stream 操作对象
                .filter((s) -> s.startsWith("s"))//对这个流做过滤操作
                .forEach(System.out::println);

        ElementType e = ElementType.TYPE_PARAMETER;


        //LocalDate
        LocalDate localDate = LocalDate.now(); //获取本地日期
        localDate = LocalDate.ofYearDay(2014, 200); // 获得 2014 年的第 200 天
        System.out.println(localDate.toString());//输出：2014-07-19
        localDate = LocalDate.of(2014, Month.SEPTEMBER, 10); //2014 年 9 月 10 日
        System.out.println(localDate.toString());//输出：2014-09-10
        //LocalTime
        LocalTime localTime = LocalTime.now(); //获取当前时间
        System.out.println(localTime.toString());//输出当前时间
        localTime = LocalTime.of(10, 20, 50);//获得 10:20:50 的时间点
        System.out.println(localTime.toString());//输出: 10:20:50
        //Clock 时钟
        Clock clock = Clock.systemDefaultZone();//获取系统默认时区 (当前瞬时时间 )
        long millis = clock.millis();//

        //float f = 3.4;
    }
}
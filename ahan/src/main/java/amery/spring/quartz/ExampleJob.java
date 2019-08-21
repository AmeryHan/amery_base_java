package amery.spring.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ameryhan
 * @date 2019/7/24 12:48
 */
public class ExampleJob {

    public void execute() {
        System.out.println("现在是" + new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
    }
}

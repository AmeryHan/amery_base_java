package amery.spring.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CronRamStoreTest {

    public static void main(String[] args) {
        System.out.println("Test start.");
        ApplicationContext context = new ClassPathXmlApplicationContext("amery/spring/quartz/cronram.xml");
        System.out.print("Test end..\n");
    }

}

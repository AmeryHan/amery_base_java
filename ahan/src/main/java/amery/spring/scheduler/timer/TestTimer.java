package amery.spring.scheduler.timer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTimer {

    public static void main(String[] args) {
        System.out.println("Test start.");
        ApplicationContext context = new ClassPathXmlApplicationContext("com/amery/spring/scheduler/timer/TestTimer-context.xml");
        System.out.print("Test end..\n");
    }
}

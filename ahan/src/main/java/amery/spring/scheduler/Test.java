package amery.spring.scheduler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "amery/spring/scheduler/scheduler.xml");
        System.out
                .println("timerFactory: " + context.getBean("springTaskDemo"));

        int i = 0;
        while (i < 10) {
            Thread.sleep(1000);
            System.out.println("..." + i++);
        }

    }

}

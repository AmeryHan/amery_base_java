package amery.spring.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RAMJobStoreTest {

    public static void main(String[] args) {
        System.out.println("Test start.");
        ApplicationContext context = new ClassPathXmlApplicationContext("amery/spring/quartz/RAMJobStore.xml");
        //如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化
        System.out.print("Test end..\n");
    }

}

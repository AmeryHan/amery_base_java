package dubbo.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * 2 * @Author: han,zhansheng
 * 3 * @Date: 2019/12/11 2:56 PM
 * 4
 */
public class ProviderApp {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("dubbo.config");
        ctx.start();
        System.out.println("Provider start...");

        System.in.read();
    }
}

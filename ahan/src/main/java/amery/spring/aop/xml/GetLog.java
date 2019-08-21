package amery.spring.aop.xml;

import org.aspectj.lang.ProceedingJoinPoint;

public class GetLog {
    public void getLog(ProceedingJoinPoint joinpoint) throws Throwable {
        String reslut = (String) joinpoint.proceed();
        //这里是记录日志的
        System.out.println("result===" + reslut);
    }
}

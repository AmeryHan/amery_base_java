package amery.spring.aop.program;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public   class  BeforeAdvice  implements  MethodBeforeAdvice {
  
        public   void  before(Method method,Object[] args, Object target)
                  throws Throwable {
           
          System.out.println( " 这是BeforeAdvice类的before方法. " );
           
       } 
}

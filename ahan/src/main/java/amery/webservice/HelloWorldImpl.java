package amery.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "HelloWorld", serviceName = "HelloWorldService")
public class HelloWorldImpl {

    @WebMethod()
    public String helloWorld(String userName) {
        return "Hello " + userName;
    }
}

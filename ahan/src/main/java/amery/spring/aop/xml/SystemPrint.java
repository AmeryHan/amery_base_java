package amery.spring.aop.xml;

public class SystemPrint implements Print {

    public String print(String name) {
        String result = "hello " + name;
        System.out.println(result);
        return result;
    }

    public String sleep(String name) {
        String result = name + " is sleep now";
        System.out.println(result);
        return result;
    }
}

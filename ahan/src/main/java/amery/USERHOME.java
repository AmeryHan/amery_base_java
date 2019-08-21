package amery;

import java.util.Properties;

public class USERHOME {

    public static void main(String[] args) {

        System.out.println(System.getProperty("user.home"));
        Properties ps = System.getProperties();
        for (Object o : ps.keySet()) {
            System.out.println(o.toString() + "----------" + System.getProperty(o.toString()));
        }
//		System.out.println(System.getProperties());
    }
}

package amery.spring.aop.xml;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class XMLTest {

    @Autowired
    private Print print;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void testXMLAOP() {
        print.print("ding");
        System.out.println("-----------------");
        print.sleep("laoding");
    }

}

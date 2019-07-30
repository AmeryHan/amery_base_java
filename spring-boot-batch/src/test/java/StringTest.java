import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * @author ameryhan
 * @date 2019/7/30 13:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class StringTest {

    @Autowired
    private String hello;


    @Test
    public void testHello() {
        System.out.println("intTest");
        Assert.isTrue("hello_xyz".equals(hello));
    }

}
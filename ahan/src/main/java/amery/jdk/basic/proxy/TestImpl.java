package amery.jdk.basic.proxy;

/**
 * @author ameryhan
 * @date 2019/9/3 17:06
 */
public class TestImpl implements Test {

    @Override
    public int test(int i) {
        return i + 1;
    }
}

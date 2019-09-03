package amery.jdk.basic.proxy;

/**
 * @author ameryhan
 * @date 2019/9/3 17:07
 */
public class DecoratorTest implements Test {
    private Test target;

    public DecoratorTest(Test target) {
        this.target = target;
    }

    @Override
    public int test(int i) {
        return target.test(i);
    }
}

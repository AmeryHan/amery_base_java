package amery.jdk.lambda;

public class SubDefaultFunClass implements DefaultFunInterface {
    public static void main(String[] args) {
        // 实例化一个子类对象，改子类对象可以直接调用父接口中的默认方法 count
        SubDefaultFunClass sub = new SubDefaultFunClass();
        sub.count();
    }
}
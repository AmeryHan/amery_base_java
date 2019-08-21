package amery.jdk.lambda;

public interface DefaultFunInterface {
    // 定义默认方法 count
    default int count() {
        return 1;
    }

    static int find() {
        return 1;
    }
}

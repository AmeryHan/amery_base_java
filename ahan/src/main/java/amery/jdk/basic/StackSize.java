package amery.jdk.basic;

/**
 * Created by ahan on 15/05/2017.
 */
public class StackSize {

    private int size = 1;

    public void stackLeak() {
        size++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        StackSize gg = new StackSize();
        try {
            gg.stackLeak();
        } catch (Throwable e) {
            System.out.println(gg.size);
            throw e;
            //e.printStackTrace();
        }
    }
}

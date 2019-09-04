package amery.jdk.basic.compare;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author ameryhan
 * @date 2019/9/4 17:27
 */
public class GirlComparator implements Comparator<Girl> {

    @Override
    public int compare(Girl g1, Girl g2) {
        return g1.getAge() - g2.getAge();
    }

    public static void main(String[] args) {
        Set<Girl> set = new TreeSet<>();
        Girl girl;
        for (int i = 0; i < 75; i++) {
            girl = new Girl("girl " + i, i);
            set.add(girl);
        }
        set.stream().forEach(System.out::println);
    }
}

package amery.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class AlgorithmExample {

    public static void main(String[] args) {
        LinkedList link = new LinkedList();
        link.add(new Integer(10));
        link.add(new Integer(35));
        link.add(new Integer(23));
        link.add(new Integer(54));
        link.add(new Integer(36));
        Comparator cmp = Collections.reverseOrder();
        Collections.sort(link, cmp);
        Iterator it = link.iterator();
        System.out.println("逆序排序的列表为： ");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("给定列表中的最大值为："+Collections.max(link));
        System.out.println("给定列表中的最小值为："+Collections.min(link));
    }
}

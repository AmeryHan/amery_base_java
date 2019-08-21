package amery.sort;

import java.util.Comparator;

public interface Sorter {
    /**
     * 排序
     *
     * @param list 待排序的数组
     */
    <T extends Comparable<T>> void sort(T[] list);

    /**
     * 排序
     *
     * @param list 待排序的数组
     * @param comp 比较两个对象的比较器
     */
    <T> void sort(T[] list, Comparator<T> comp);
}

package amery.interview.sortbigfile;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ArrayMergerSortTask extends RecursiveAction {

    // implementation details follow:
    static final int THRESHOLD = 1000;

    final int[] array;
    final int lo, hi;

    ArrayMergerSortTask(int[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    ArrayMergerSortTask(int[] array) {
        this(array, 0, array.length);
    }

    protected void compute() {
        if (hi - lo < THRESHOLD)		//小于1000，就排序
            sortSequentially(lo, hi);
        else {
            int mid = (lo + hi) >>> 1;		//大于1000，拆分
            invokeAll(new ArrayMergerSortTask(array, lo, mid),
                    new ArrayMergerSortTask(array, mid, hi));
            merge(lo, mid, hi);
        }
    }

    void sortSequentially(int lo, int hi) {
        Arrays.sort(array, lo, hi);		//利用JDK自带的排序进行
    }

    void merge(int lo, int mid, int hi) {
        int[] buf = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++)
            array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
    }

}

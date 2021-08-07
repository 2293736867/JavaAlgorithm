package test.sort;

import com.company.sort.Sort;
import com.company.sort.algorithm.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class SortTest {
    private static final int TIMES = 1_000;
    private static final int MAX_LEN = 1_000;
    private static final int COUNTING_SORT_ELEMENT_MAX = 100_000;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        test(new BubbleSort(),"Bubble sort error.");
        test(new CountingSort(),"Counting sort error.");
        test(new HeapSort(),"Heap sort error.");
        test(new MergeSort(),"Merge sort error.");
        test(new QuickSort(),"Quick sort error.");
        test(new SelectionSort(),"Selection sort error.");
    }

    private static void test(Sort sort,String message){
        boolean errors = test(sort);
        if(errors){
            System.out.println(message);
        }
    }

    private static boolean test(Sort sort) {
        AtomicReference<Boolean> errors = new AtomicReference<>(false);
        Random random = new Random();
        IntStream.range(0, TIMES).parallel().forEach(ii -> {
            int n = random.nextInt(MAX_LEN);
            int[] arr = new int[n];
            for (int i = 0; i < n; ++i) {
                if(sort.getClass() == CountingSort.class){
                    arr[i] = random.nextInt(COUNTING_SORT_ELEMENT_MAX);
                }else{
                    arr[i] = random.nextInt();
                }
            }
            lock.lock();
            sort.setArr(arr);
            int[] sortedArr = sort.sort();
            boolean res = checkSortArray(sortedArr);
            if (!res) {
                System.out.println("---------------------------Error--------------------------");
                System.out.println("arr is " + Arrays.toString(arr));
                System.out.println("sorted arr is " + Arrays.toString(sortedArr));
                System.out.println("---------------------------Error finish--------------------------");
                System.out.println(sort.getClass().getName());
                errors.set(true);
            }
            lock.unlock();
        });
        return errors.get();
    }

    private static boolean checkSortArray(int[] s) {
        int n = s.length;
        for (int i = 0; i < n - 1; i++) {
            if (s[i] > s[i + 1]) {
                System.out.println("i is "+i);
                System.out.println("s[i] is "+s[i]);
                System.out.println("s[i+1] is "+s[i+1]);
                return false;
            }
        }
        return true;
    }
}

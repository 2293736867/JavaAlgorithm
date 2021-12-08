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
    private static final int TEST_TIMES = 1_000;
    private static final int TEST_ARRAY_MAX_LEN = 1_000;
    private static final int COUNTING_SORT_ELEMENT_MAX = 100_000;

    public static void main(String[] args) {
        test(new InsertionSort());
        test(new BubbleSort());
        test(new CountingSort());
        test(new HeapSort());
        test(new MergeSort());
        test(new QuickSort());
        test(new SelectionSort());
    }

    private static void test(Sort sort) {
        new Thread(() -> {
            AtomicReference<Boolean> errors = new AtomicReference<>(false);
            Random random = new Random();
            final Lock lock = new ReentrantLock();
            IntStream.range(0, TEST_TIMES).parallel().forEach(ii -> {
                int n = random.nextInt(TEST_ARRAY_MAX_LEN);
                int[] arr = new int[n];
                for (int i = 0; i < n; ++i) {
                    if (sort.getClass() == CountingSort.class) {
                        arr[i] = random.nextInt(COUNTING_SORT_ELEMENT_MAX);
                    } else {
                        arr[i] = random.nextInt();
                    }
                }
                lock.lock();
                sort.setArr(arr);
                int[] sortedArr = sort.sort();
                lock.unlock();
                boolean res = checkSortArray(sortedArr);
                if (!res) {
                    System.out.println("---------------------------Error--------------------------");
                    System.out.println("arr is " + Arrays.toString(arr));
                    System.out.println("sorted arr is " + Arrays.toString(sortedArr));
                    System.out.println("---------------------------Error finish--------------------------");
                    System.out.println(sort.getClass().getSimpleName());
                    errors.set(true);
                }
            });
            if (errors.get()) {
                System.out.println(sort.getClass().getSimpleName() + " error.");
            } else {
                System.out.println(sort.getClass().getSimpleName() + " test finish, no error.");
            }
        }).start();
    }

    private static boolean checkSortArray(int[] s) {
        int n = s.length;
        for (int i = 0; i < n - 1; i++) {
            if (s[i] > s[i + 1]) {
                System.out.println("i is " + i);
                System.out.println("s[i] is " + s[i]);
                System.out.println("s[i+1] is " + s[i + 1]);
                return false;
            }
        }
        return true;
    }
}

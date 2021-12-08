package com.company.sort;

import java.util.Arrays;

public abstract class Sort {
    protected int[] arr;
    protected int n;
    private static final int CAN_DO_SORT = 2;

    public Sort() {
        setArr(new int[0]);
    }

    public Sort(int[] arr) {
        setArr(arr);
    }

    public void setArr(int[] arr) {
        this.n = arr.length;
        this.arr = Arrays.copyOf(arr, n);
    }

    private int preCheck() {
        if (arr == null) {
            return -1;
        }
        if (n <= 1) {
            return n;
        }
        return CAN_DO_SORT;
    }

    public int[] sort() {
        return preCheck() == CAN_DO_SORT ? doSort() : arr;
    }

    protected abstract int[] doSort();
}

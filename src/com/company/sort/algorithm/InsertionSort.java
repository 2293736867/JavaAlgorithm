package com.company.sort.algorithm;

import com.company.sort.Sort;

public class InsertionSort extends Sort {
    public InsertionSort() {
    }

    public InsertionSort(int[] arr) {
        super(arr);
    }

    @Override
    protected int[] doSort() {
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}

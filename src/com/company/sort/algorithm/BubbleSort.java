package com.company.sort.algorithm;

import com.company.sort.Sort;

public class BubbleSort extends Sort {

    public BubbleSort() {

    }

    public BubbleSort(int[] arr) {
        super(arr);
    }

    @Override
    protected int[] doSort() {
        for (int i = n - 1; i >= 1; --i) {
            for (int j = 0; j < i; j++) {
                if (arr[j+1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}

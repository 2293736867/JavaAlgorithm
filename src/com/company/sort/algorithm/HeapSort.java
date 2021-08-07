package com.company.sort.algorithm;

import com.company.sort.Sort;

public class HeapSort extends Sort {
    public HeapSort() {

    }

    public HeapSort(int[] arr) {
        super(arr);
    }

    @Override
    protected int[] doSort() {
        for (int i = n / 2 - 1; i >= 0; --i) {
            adjust(i, n);
        }
        for (int i = 1; i < n; i++) {
            swap(0, n - i);
            adjust(0, n - i);
        }
        return arr;
    }

    private void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private void adjust(int i, int len) {
        int l = i * 2 + 1;
        int r = (i + 1) * 2;
        if (r < len && arr[i] < arr[r] && arr[l] < arr[r]) {
            swap(i, r);
            adjust(r, len);
        } else if (l < len && arr[i] < arr[l]) {
            swap(i, l);
            adjust(l, len);
        }
    }
}

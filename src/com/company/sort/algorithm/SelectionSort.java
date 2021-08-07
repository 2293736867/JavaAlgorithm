package com.company.sort.algorithm;

import com.company.sort.Sort;

public class SelectionSort extends Sort {

    public SelectionSort(){

    }

    public SelectionSort(int []arr){
        super(arr);
    }

    @Override
    protected int[] doSort() {
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }
}

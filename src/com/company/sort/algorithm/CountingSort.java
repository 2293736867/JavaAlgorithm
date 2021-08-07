package com.company.sort.algorithm;

import com.company.sort.Sort;

public class CountingSort extends Sort {
    public CountingSort(){

    }

    public CountingSort(int []arr){
        super(arr);
    }

    @Override
    protected int[] doSort() {
        int max = -1;
        for(int ss: arr){
            if(Math.abs(ss) > max){
                max = Math.abs(ss);
            }
        }
        int [] positive = new int[max+1];
        int [] negative = new int[max+1];
        for (int ss: arr){
            if(ss >= 0){
                ++positive[ss];
            }else{
                ++negative[Math.abs(ss)];
            }
        }
        int [] result = new int[n];
        int resultIndex = 0;
        for (int i = max; i >= 0; --i) {
            while (negative[i] != 0) {
                result[resultIndex++] = -i;
                --negative[i];
            }
        }
        for (int i = 0; i <= max; i++) {
            while (positive[i] != 0){
                result[resultIndex++] = i;
                --positive[i];
            }
        }
        return result;
    }
}

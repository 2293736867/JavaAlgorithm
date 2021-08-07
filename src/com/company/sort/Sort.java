package com.company.sort;

import java.util.Arrays;

public abstract class Sort {
    protected int [] arr;
    protected int n;

    public Sort(){

    }

    public Sort(int [] arr){
        this.n = arr.length;
        this.arr = Arrays.copyOf(arr,n);
    }

    public void setArr(int [] arr){
        this.n = arr.length;
        this.arr = Arrays.copyOf(arr,n);
    }

    private int preCheck(){
        if(arr == null){
            return -1;
        }
        if(arr.length == 0){
            return 0;
        }
        if(arr.length == 1){
            return 1;
        }
        return 2;
    }

    public int[] sort(){
        int preCheckRes = preCheck();
        if(preCheckRes != 2){
            return arr;
        }
        return doSort();
    }

    protected abstract int[] doSort();
}

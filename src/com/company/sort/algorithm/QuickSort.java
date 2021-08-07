package com.company.sort.algorithm;

import com.company.sort.Sort;

public class QuickSort extends Sort {

    public QuickSort(){

    }

    public QuickSort(int []arr){
        super(arr);
    }

    @Override
    public int[] doSort(){
        quickSort(0,n-1);
        return arr;
    }

    private void quickSort(int l,int r){
        if(l < r){
            int m = partition(l,r);
            quickSort(l,m-1);
            quickSort(m+1,r);
        }
    }

    private int partition(int l,int r){
        int ll = l;
        int rr = r;
        while(ll < rr){
            while (ll < rr && arr[l] <= arr[rr]){
                --rr;
            }
            while(ll < rr && arr[l] >= arr[ll]){
                ++ll;
            }
            swap(ll,rr);
        }
        swap(l,ll);
        return ll;
    }

    private void swap(int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

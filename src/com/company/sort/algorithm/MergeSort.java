package com.company.sort.algorithm;

import com.company.sort.Sort;

import java.util.Arrays;

public class MergeSort extends Sort {

    public MergeSort(){

    }

    public MergeSort(int []arr){
        super(arr);
    }

    @Override
    protected int[] doSort() {
        int[] l = divide(0,n/2);
        int[] r = divide(n/2,n);
        return merge(l,r);
    }

    private int[] merge(int []l,int []r){
        int lLen = l.length;
        int rLen = r.length;
        int [] result = new int[lLen+rLen];
        int i = 0;
        int j = 0;
        int sIndex = 0;
        while(i < lLen && j < rLen){
            if(l[i] > r[j]){
                result[sIndex++] = r[j];
                ++j;
            }else{
                result[sIndex++] = l[i];
                ++i;
            }
        }
        while(i < lLen){
            result[sIndex++] = l[i++];
        }
        while(j < rLen){
            result[sIndex++] = r[j++];
        }
        return result;
    }

    private int[] divide(int l,int r){
        if(r - l > 1){
            int m = (l+r)/2;
            int [] lNum = divide(l,m);
            int [] rNum = divide(m,r);
            return merge(lNum,rNum);
        }else{
            return Arrays.copyOfRange(arr,l,r);
        }
    }
}

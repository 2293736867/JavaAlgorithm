package com.company.select;

import java.util.Random;

// 选择出一个数组中的第k个最大元素
// k的范围是[1,n]
// 以下代码已通过力扣215题测试
// https://leetcode.cn/problems/kth-largest-element-in-an-array/
public class QuickSelect {
    private static final Random random = new Random();

    public int select(int k, int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return -1;
        }
        return quickSelect(0, n - 1, n - k, arr);
    }

    private int quickSelect(int l, int r, int k, int[] arr) {
        int m = partition(l, r, arr);
        if (m == k) {
            return arr[m];
        }
        if (m > k) {
            return quickSelect(l, m - 1, k, arr);
        } else {
            return quickSelect(m + 1, r, k, arr);
        }
    }

    private int partition(int l, int r, int[] arr) {
        int randomIndex = random.nextInt(r - l + 1) + l;
        int pivot = arr[randomIndex];
        arr[randomIndex] = arr[l];
        arr[l] = pivot;
        int origin = l++;
        while (l <= r) {
            while (l <= r && arr[l] <= pivot) {
                ++l;
            }
            while (l <= r && arr[r] >= pivot) {
                --r;
            }
            if (l < r) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }
        --l;
        arr[origin] = arr[l];
        arr[l] = pivot;
        return l;
    }
}

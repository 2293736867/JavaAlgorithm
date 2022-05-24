package test.select;

import com.company.select.QuickSelect;

// QuickSelect代码已通过力扣215题测试
// https://leetcode.cn/problems/kth-largest-element-in-an-array/
// 此处仅进行简单测试

public class SelectTest {
    public static void main(String[] args) {
        QuickSelect select = new QuickSelect();
        int[] arr = {3,2,1,5,6,4};
        System.out.println(select.select(2, arr));
    }
}

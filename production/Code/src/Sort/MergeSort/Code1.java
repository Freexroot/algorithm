package Sort.MergeSort;

import utils.ProgressBar;
import utils.RandomValue;

import java.util.Arrays;

/*
归并排序:https://leetcode-cn.com/problems/count-of-range-sum/
给你一个整数数组nums以及两个整数lower和upper。求数组中，值位于范围[lower,upper]（包含lower和upper）之内的区间和的个数。
区间和S(i,j)表示在nums中，位置从i到j的元素之和，包含i和j(i≤j)。
 */
public class Code1 {

    //前缀和辅助数组
    public static int countRangeSum(int[] nums, int lower, int upper, int t) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        if (t == 1) {
            return process(sum, 0, nums.length - 1, lower, upper);
        } else {
            return process2(sum, 0, nums.length - 1, lower, upper);
        }
    }

    //解法1.暴力o(N^2)
    public static int process(long[] sum, int L, int R, int lower, int upper) {
        int len = sum.length;
        long t = 0, count = 0;
        for (int i = 0; i < len; i++) {
            for (int i1 = 0; i1 <= i; i1++) {
                if (i == i1) {
                    t = sum[i];
                } else {
                    t = sum[i] - sum[i1];
                }
                if (t >= lower && t <= upper) {
                    count++;
                }
            }
        }

        return (int) count;
    }

    //解法2.利用前缀和递归
    public static int process2(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            if (sum[L] >= lower && sum[L] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        int M = L + ((R - L) >> 1);
        int count1 = process2(sum, L, M, lower, upper);
        int count2 = process2(sum, M + 1, R, lower, upper);
        return count1 + count2 + merge(sum, L, M, R, lower, upper);
    }

    public static int merge(long[] sum, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L;
        for (int i = M + 1; i <= R; i++) {
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            //找到右组的左边界
            while (windowL <= M && sum[windowL] < min) {
                windowL++;
            }
            //找到右组的右边界
            while (windowR <= M && sum[windowR] <= max) {
                windowR++;
            }
            ans += windowR - windowL;
        }
        //左右数组排序,因为L...R范围内的符合条件都已经找到了，所以顺序打乱也可以
        int i = 0;
        long help[] = new long[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = sum[p1] >= sum[p2] ? sum[p2++] : sum[p1++];
        }
        while (p1 <= M) {
            help[i++] = sum[p1++];
        }
        while (p2 <= R) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[L + i] = help[i];
        }
        return ans;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            ProgressBar.progressBar(10000, i);
            int[] arr = RandomValue.randomIntArrayDisorder(10000, Integer.MAX_VALUE / 2, false, false);
            int p1 = (int) (Math.random() * 100000) * (Math.random() > 0.5 ? 1 : -1);
            int p2 = (int) (Math.random() * 100000) * (Math.random() > 0.5 ? 1 : -1);
            if (p1 > p2) {
                p1 = p1 ^ p2;
                p2 = p1 ^ p2;
                p1 = p1 ^ p2;
            }

            int n1 = countRangeSum(arr, p1, p2, 1);
            int n2 = countRangeSum(arr, p1, p2, 2);

            if (n1 != n2) {
                System.out.println("错误：" + n1 + "  " + n2);
                System.out.println(p1 + "  " + p2);
                System.out.println(Arrays.toString(arr));
                countRangeSum(arr, p1, p2, 2);
                return;
            }
        }
    }
}

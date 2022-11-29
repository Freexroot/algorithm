package class24;

import java.util.LinkedList;

/*
给定一个整型数组arr，和一个整数num
某个arr中的子数组sub，如果想达标，必须满足：sub中最大值 – sub中最小值 <= num，
返回arr中达标子数组的数量
* */
public class Code02_AllLessNumSubArray {
    //暴力
    public static int allLessNumSubArray1(int[] arr, int num) {
        if (arr.length == 0 || num < 0) {
            return 0;
        }
        int n = arr.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                for (int k = i; k <= j; k++) {
                    int max, min;
                    max = min = arr[k];
                    max = Math.max(max, arr[k]);
                    min = Math.min(min, arr[k]);
                    if (max - min <= sum) {
                        sum++;
                    }
                }

            }
        }
        return sum;
    }

    //O(1)
    public static int allLessNumSubArray2(int[] arr, int num) {
        if (arr.length == 0 || num < 0) {
            return 0;
        }
        LinkedList<Integer> minLinkedList = new LinkedList();
        LinkedList<Integer> maxLinkedList = new LinkedList();
        int n = arr.length;
        int index = 0;//窗口右边
        int sum = 0;//答案
        int min, max;
        for (int i = 0; i < n; i++) {
            while (index < n) {
                //弹出
                if (!minLinkedList.isEmpty() && minLinkedList.peekFirst() == i - 1) {
                    minLinkedList.pollFirst();
                }
                if (!maxLinkedList.isEmpty() && maxLinkedList.peekFirst() == i - 1) {
                    maxLinkedList.pollFirst();
                }

                //插入
                while (!minLinkedList.isEmpty() && arr[minLinkedList.peekFirst()] >= arr[index]) {
                    minLinkedList.pollFirst();
                }
                minLinkedList.addLast(index);
                while (!maxLinkedList.isEmpty() && arr[maxLinkedList.peekFirst()] <= arr[index]) {
                    maxLinkedList.pollFirst();
                }
                maxLinkedList.addLast(index);
                //不符合条件的弹出
                if (!(arr[maxLinkedList.peekFirst()] - arr[minLinkedList.peekFirst()] <= num)) {
                    break;
                }
                index++;
            }
            sum += index - i;
        }
        return sum;
    }

    //下面是为了测试
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 1000;
        int value = 1000;
        int lenMax = 10000;
        System.out.println("run code");
        for (int i = 0; i < lenMax; i++) {
            int[] arr = randomArray(len, value);
            int W = (int) (Math.random() * len);
            int sum1 = allLessNumSubArray1(arr, W);
            int sum2 = allLessNumSubArray2(arr, W);
            if (sum1 == sum2) {
                System.out.println("error");
                System.out.println(sum1);
                System.out.println(sum2);
                return;
            }
        }
        System.out.println("end code");
    }

}

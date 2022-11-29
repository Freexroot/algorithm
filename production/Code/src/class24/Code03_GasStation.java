package class24;

import java.util.Arrays;
import java.util.LinkedList;

/*
* 加油站的良好出发点问题
有两个数组,gas[]储存的油，cost[]表示到下一个加油站的距离，下标表示加油站。请找到所有能转一圈的加油站起始点
* */
public class Code03_GasStation {
    //暴力
    public static boolean[] gasStation1(int[] gas, int[] cost) {
        int n = gas.length;
        boolean[] b = new boolean[n];//答案
        int[] arr = new int[n];//当前加油站形势到下一个加油站后的剩余油量
        int index;
        int num;

        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
        }

        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                index = (i + 1) % n;
                num = arr[i];
                while (index != i) {
                    num += arr[index];
                    if (num < 0) {
                        break;
                    }
                    index = (index + 1) % n;
                }
                if (index == i) {
                    b[i] = true;
                }
            }
        }
        return b;
    }

    //窗口内最大值或最小值
    public static boolean[] gasStation2(int[] gas, int[] cost) {
        int n = gas.length;
        boolean[] b = new boolean[n];//答案
        int[] arr = new int[2 * n];//当前加油站形势到下一个加油站后的剩余油量
        int index = 0;
        int num;
        int min;

        for (int i = 0; i < 2 * n; i++) {
            if (i < n) {
                arr[i] = gas[i] - cost[i];
            } else {
                arr[i] = arr[i - n];
            }
        }
        for (int i = 1; i < 2 * n; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }

        LinkedList<Integer> minLinkedList = new LinkedList();
        for (int i = 0; i < n; i++) {
            //窗口左边
            if (!minLinkedList.isEmpty() && minLinkedList.peekFirst() == i-1) {
                minLinkedList.pollFirst();
            }

            while (index < i + n) {
                //窗口右边
                while (!minLinkedList.isEmpty() && arr[minLinkedList.peekLast()] >= arr[index]) {
                    minLinkedList.pollLast();
                }
                minLinkedList.addLast(index);
                index++;
            }
            min = minLinkedList.peekFirst();
            if (i == 0 && arr[min] >= 0) {
                b[i] = true;
            } else if(i>0&&arr[min] - arr[i - 1] >= 0) {
                b[i] = true;
            }
        }
        return b;
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
        int value = 1000;
        int lenMax = 10000;
        System.out.println("run code");
        for (int i = 0; i < lenMax; i++) {
            int len = (int) (Math.random() * 10);
            int[] arr1 = randomArray(len, value);
            int[] arr2 = randomArray(len, value);
            boolean[] b1 = gasStation1(arr1, arr2);
            boolean[] b2 = gasStation2(arr1, arr2);
            if (!Arrays.equals(b1,b2)) {
                System.out.println(Arrays.toString(b1));
                System.out.println(Arrays.toString(b2));
                gasStation1(arr1, arr2);
                gasStation2(arr1, arr2);
                System.out.println("error");
                return;
            }
        }
        System.out.println("end code");
    }

}

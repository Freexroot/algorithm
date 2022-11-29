package class24;

import java.util.Arrays;
import java.util.LinkedList;

/*
窗口内最大值或最小值更新结构的实现
假设一个固定大小为W的窗口，依次划过arr，
返回每一次滑出状况的最大值
例如，arr = [4,3,5,4,3,3,6,7], W = 3
返回：[5,5,5,4,6,7]
* */
public class Code01_SlidingWindowMaxArray {
    //窗口内最大值
    public static int[] slidingWindowMaxArray1(int[] arr, int W) {
        if (arr == null || arr.length < W || arr.length == 0 || W == 0) {
            return null;
        }

        int N = arr.length;
        int[] maxArr = new int[N - W + 1];//答案
        int index = 0;//maxArr的下标
        LinkedList<Integer> maxQueue = new LinkedList<Integer>();

        for (int i = 0; i < N; i++) {
            //严格要求从大到小
            while (!maxQueue.isEmpty() && (arr[maxQueue.peekLast()] <= arr[i])) {
                maxQueue.pollLast();
            }
            maxQueue.addLast(i);
            //窗口大小只有W，所以妹增加一个值就要删除一个值
            if (i >= W && maxQueue.peekFirst() == i - W) {
                maxQueue.pollFirst();
            }

            if (i >= W - 1) {
                maxArr[index++] = arr[maxQueue.peekFirst()];
            }
        }
        return maxArr;
    }

    //暴力
    public static int[] slidingWindowMaxArray2(int[] arr, int W) {
        if (arr == null || arr.length < W || arr.length == 0 || W == 0) {
            return null;
        }

        int N = arr.length;
        int[] maxArr = new int[N - W + 1];//答案
        int index = 0;//maxArr的下标
        int max = 0;
        for (int i = 0; i < N - W + 1; i++) {
            for (int j = 0; j < W; j++) {
                max = Math.max(max, arr[i + j]);
            }
            maxArr[index++] = max;
            max = 0;
        }
        return maxArr;
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
        int len = 100;
        int value = 100;
        int lenMax = 10000;
        for (int i = 0; i < lenMax; i++) {
            int[] arr = randomArray(len, value);
            int W = (int) (Math.random() * len);
            int[] maxArr1 = slidingWindowMaxArray1(arr, W);
            int[] maxArr2 = slidingWindowMaxArray2(arr, W);
            if (!Arrays.equals(maxArr1, maxArr2)) {
                System.out.println(Arrays.toString(maxArr1));
                System.out.println(Arrays.toString(maxArr2));
            }
        }

    }
}

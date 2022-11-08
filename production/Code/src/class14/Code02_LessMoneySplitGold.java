package class14;

import utils.RandomValue;

import java.util.PriorityQueue;

//哈夫曼编码问题
//给出一个整数数组，里面有任意多数字，把他们都加起来得到整数和sum，现在分割这个sum，直到分割出数组里的数字为止，
// 没分割一次都会花费当前长度的代价，求代价之和最小的分割方法。
public class Code02_LessMoneySplitGold {
    //贪心
    public static int lessMoneySplitGold1(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return 0;
        }
        //放入小根堆
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int minCost = 0;//最小代价
        int cost;
        //合并完成就弹出
        while (pQ.size() != 1) {
            cost = pQ.poll() + pQ.poll();
            minCost += cost;
            pQ.add(cost);
        }

        return minCost;
    }

    //暴力
    public static int lessMoneySplitGold2(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return 0;
        }
        return porcess(arr, 0);
    }

    public static int porcess(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int i1 = i+1; i1 < arr.length; i1++) {
                min = Math.min(min, porcess(copyAndMergeTwo(arr, i, i1), pre + arr[i] + arr[i1]));
            }
        }
        return min;
    }

    public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] copyArr = new int[arr.length - 1];
        int j1 = 1;
        copyArr[0] = arr[i] + arr[j];
        for (int i1 = 0; i1 < arr.length; i1++) {
            if (i1 != i && i1 != j) {
                copyArr[j1] = arr[i1];
                j1++;
            }
        }
        return copyArr;
    }

    public static void main(String[] args) {
        int lenMax = 6;
        int valueMax=100;
        int testTime=10000;
        for (int i = 0; i < testTime; i++) {
            int[] ints = RandomValue.randomIntArrayDisorder(lenMax, valueMax, true, false);
            if(lessMoneySplitGold1(ints)!=lessMoneySplitGold2(ints)){
                System.out.println("error");
            }
        }
        System.out.println("finish");
    }
}

package class20;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
* 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
只有一台洗咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
假设所有人拿到咖啡之后立刻喝干净，
返回从开始等到所有咖啡机变干净的最短时间
三个参数：int[] arr、int N，int a、int b
* */
public class Code03_Coffee {
    static class MyComparator implements Comparator<Spend> {
        @Override
        public int compare(Spend o1, Spend o2) {
            return (o1.nowTime + o1.waitingTime) - (o2.nowTime + o2.waitingTime);
        }
    }

    //喝咖啡花费时间
    static class Spend {
        public int nowTime; //现在时间
        public int waitingTime;//等待时间

        public Spend(int nowTime, int waitingTime) {
            this.nowTime = nowTime;
            this.waitingTime = waitingTime;
        }
    }

    //第一部分，计算n位客人喝咖啡完的时间
    public static int drinkCoffee(int[] arr, int n, int a, int b) {
        if (arr == null || arr.length == 0 || n <= 0 || a <= 0 || b <= 0) {
            return 0;
        }
        //堆 模拟排队
        Queue<Spend> queue = new PriorityQueue<>(new MyComparator());
        //初视化
        for (int i = 0; i < arr.length; i++) {
            queue.add(new Spend(0, arr[i]));
        }

        int[] time = new int[n];
        Spend spend;
        for (int i = 0; i < n; i++) {
            spend = queue.poll();//拿出时间最短的队伍
            spend.nowTime += spend.waitingTime;//推进时间
            time[i] = spend.nowTime;
            queue.add(spend);
        }
        //暴力递归
        //return washCoffee(time, 0, 0, a, b);
        //动态规划
        return dp(time, n, a, b);
    }

    //暴力递归
    //洗咖啡,index第几个人,free是洗咖啡机可以使用的时间,a是洗的时间，b是自然风干的时间
    private static int washCoffee(int[] time, int index, int free, int a, int b) {
        if (index == time.length) {
            return 0;
        }
        //选择机器洗
        int c1 = Math.max(time[index], free) + a;
        int p1 = Math.max(washCoffee(time, index + 1, c1, a, b), c1);

        //选择风干
        int c2 = time[index] + b;
        int p2 = Math.max(washCoffee(time, index + 1, free, a, b), c2);

        return Math.min(p1, p2);
    }

    private static int dp(int[] arr, int n, int a, int b) {
        //计算最大洗咖啡可用时间
        int free = 0;
        for (int i = 0; i < n; i++) {
            free = Math.max(free, arr[i]) + a;
        }
        free = Math.max(free, arr[n - 1] + b);

        int[][] dp = new int[n + 1][free + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= free; j++) {

                //选择机器洗
                int c1 = Math.max(arr[i], j) + a;
                if (c1 > free) {//越界
                    break;
                }
                int p1 = Math.max(dp[i + 1][c1], c1);

                //选择风干
                int c2 = arr[i] + b;
                int p2 = Math.max(dp[i + 1][j], c2);
                dp[i][j] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(drinkCoffee(new int[]{1, 3, 5, 7}, 10, 2, 4));
    }
}

package class14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
输入:正数数组costs、正数数组profits、正数K、正数M

costs[i]表示i号项目的花费
profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
K表示你只能串行的最多做k个项目
M表示你初始的资金
说明:每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。输出︰你最后获得的最大钱数。
*/
public class Code04_IPO {
    static class Spend {
        int costs;//成本
        int profits;//利润

        public Spend(int costs, int profits) {
            this.costs = costs;
            this.profits = profits;
        }
    }

    //贪心
    public static int iop1(int[] costs, int[] profits, int K, int M) {
        Queue<Spend> descendingQueue = new PriorityQueue(new descendingComparator());//降序
        Queue<Spend> ascendingQueue = new PriorityQueue(new ascendingComparator());//升序
        //排序
        for (int i = 0; i < costs.length; i++) {
            Spend spend = new Spend(costs[i], profits[i]);
            ascendingQueue.add(spend);
        }
        int sum = 0;
        for (int i = 0; i < K; i++) {
            //倒入另一个堆里
            while (!ascendingQueue.isEmpty() && ascendingQueue.peek().costs < M) {
                descendingQueue.add(ascendingQueue.poll());
            }
            if (!descendingQueue.isEmpty()) {
                M += descendingQueue.poll().profits;//弹出并增加利润到手中
            }
        }
        return M;
    }

    //利润降序
    static class descendingComparator implements Comparator<Spend> {
        @Override
        public int compare(Spend o1, Spend o2) {
            return o2.profits - o1.profits;
        }
    }

    //花费升序
    static class ascendingComparator implements Comparator<Spend> {
        @Override
        public int compare(Spend o1, Spend o2) {
            return o1.costs - o2.costs;
        }
    }

    //暴力
    public static int iop2(int[] costs, int[] profits, int K, int M) {
        if (costs == null || costs.length == 0 || K == 0) {
            return 0;
        }
        ArrayList<Spend> arr = new ArrayList<>();
        for (int i = 0; i < costs.length; i++) {
            Spend spend = new Spend(costs[i], profits[i]);
            arr.add(spend);
        }
        return porcess(arr, K, M);
    }

    public static int porcess(ArrayList<Spend> arr, int K, int M) {
        if (K == 0) {
            return M;
        }
        int max = M;
        for (int i = 0; i < K; i++) {
            max = Math.max(max, porcess(arr, K - 1, M + arr.get(i).profits));
        }
        return max;
    }

    // for test
    public static int[] generatePrograms(int lenInt, int timeMax) {
        int[] arr = new int[lenInt];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * timeMax);
        }
        return arr;
    }

    public static void main(String[] args) {
        int timeMax = 100;
        int timeTimes = 10000;
        for (int i = 0; i < timeTimes; i++) {
            int programSize = (int) (Math.random() * 50);

            int[] costs = generatePrograms(programSize, timeMax);
            int[] profits = generatePrograms(programSize, timeMax);
            int K = (int) (Math.random() * timeMax);
            int M = (int) (Math.random() * timeMax);

            if (iop1(costs, profits,K,M) != iop1(costs, profits,K,M)) {
                System.out.println("Oops!");
                iop1(costs, profits,K,M);
                iop1(costs, profits,K,M);
            }
        }
        System.out.println("finish!");
    }
}

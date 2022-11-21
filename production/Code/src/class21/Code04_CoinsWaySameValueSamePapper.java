package class21;

import java.util.HashMap;

/*
* arr是货币数组，其中的值都是正数。再给定一个正数aim。
每个值都认为是一张货币，
认为值相同的货币没有任何不同，
返回组成aim的方法数
例如：arr = {1,2,1,1,2,1,2}，aim = 4
方法：1+1+1+1、1+1+2、2+2
一共就3种方法，所以返回3
* */
public class Code04_CoinsWaySameValueSamePapper {

    public static int coinWays(int[] arr, int aim) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }

        int[] coins = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int i = 0;
        for (Integer key : map.keySet()) {
            coins[i] = key;
            zhangs[i] = map.get(key);
            i++;
        }
        return process1(coins, zhangs, 0, aim);
    }

    private static int process1(int[] coins, int[] zhangs, int index, int aim) {
        if (index == coins.length) {
            return aim == 0 ? 1 : 0;
        }
        int sum = 0;
        for (int i = 0; coins[index] * i <= aim && i <= zhangs[index]; i++) {
            sum += process1(coins, zhangs, index + 1, aim - (coins[index] * i));
        }
        return sum;
    }

    private static int dp1(int[] arr, int aim) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }

        int[] coins = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int i = 0;
        for (Integer key : map.keySet()) {
            coins[i] = key;
            zhangs[i] = map.get(key);
            i++;
        }
        return process2(coins, zhangs, aim);
    }

    private static int process2(int[] coins, int[] zhangs, int aim) {
        int n = coins.length;
        int[][] dp = new int[n + 1][aim + 1];

        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                for (int k = 0; coins[i] * k <= j && k <= zhangs[i]; k++) {
                    dp[i][j] += dp[i + 1][j - (coins[i] * k)];
                }
            }
        }
        return dp[0][aim];
    }

    //优化枚举
    private static int dp2(int[] arr, int aim) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }

        int[] coins = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int i = 0;
        for (Integer key : map.keySet()) {
            coins[i] = key;
            zhangs[i] = map.get(key);
            i++;
        }
        return process3(coins, zhangs, aim);
    }

    private static int process3(int[] coins, int[] zhangs , int aim) {
        int n = coins.length;
        int[][] dp = new int[n + 1][aim + 1];

        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]];
                    dp[i][j] -= j - (coins[i] * (zhangs[i]+1)) <= 0 ? 0 :dp[i][j - (coins[i] * zhangs[i]+1)];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
//        System.out.println(coinWays(new int[]{1, 2, 1, 1, 2, 1, 2}, 4));
//        System.out.println(dp1(new int[]{1,2,1,1,2,1,2}, 4));
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 && ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                 dp1(arr, aim);

                break;
            }
        }
        System.out.println("测试结束");
    }

}

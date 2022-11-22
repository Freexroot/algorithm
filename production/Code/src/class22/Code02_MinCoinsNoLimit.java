package class22;

/*
* arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
每个值都认为是一种面值，且认为张数是无限的。
返回组成aim的最少货币数（张数）
* */
public class Code02_MinCoinsNoLimit {
    public static int minCoinsNoLimit(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int aim) {
        if (aim < 0) {
            return Integer.MAX_VALUE;
        }
        if (index == arr.length) {
            return aim == 0 ? 0 : Integer.MAX_VALUE;
        }

        int num = Integer.MAX_VALUE;
        for (int i = 0; arr[index] * i <= aim; i++) {
            int p = process(arr, index + 1, aim - arr[index] * i);
            if (p != Integer.MAX_VALUE) {
                num = Math.min(num, p + i);
            }
        }
        return num;
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];

        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {

                int num = Integer.MAX_VALUE;
                for (int k = 0; arr[i] * k <= j; k++) {
                    int p = dp[i + 1][j - arr[i] * k];
                    if (p != Integer.MAX_VALUE) {
                        num = Math.min(num, p + k);
                    }
                }
                dp[i][j] = num;

            }
        }
        return dp[0][aim];
    }


    private static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];

        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j];
                //只有左边存在，并且不是最大值是才判断
                if (arr[i] <= j && dp[i][j - arr[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - arr[i]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
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

        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoinsNoLimit(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }


}
